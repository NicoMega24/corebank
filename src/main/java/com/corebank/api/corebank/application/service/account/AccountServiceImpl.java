package com.corebank.api.corebank.application.service.account;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corebank.api.corebank.application.exceptions.AccountNotFoundException;
import com.corebank.api.corebank.application.service.ledger.LedgerService;
import com.corebank.api.corebank.application.service.transaction.TransactionService;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.enums.TransactionType;
import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.domain.model.IdempotencyKey;
import com.corebank.api.corebank.domain.model.Transaction;
import com.corebank.api.corebank.infrastructure.persistence.AccountRepository;
import com.corebank.api.corebank.infrastructure.persistence.IdempotencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final LedgerService ledgerService;
    private final IdempotencyRepository idempotencyRepository;

    @Override
    public Account createAccount(
            String accountNumber,
            Long customerId,
            CurrencyEnum currency,
            AccountTypeEnum accountType) {

        Account account = new Account(
                accountNumber,
                customerId,
                currency,
                accountType
        );

        return accountRepository.save(account);
    }

    @Override
    public void deposit(Long accountId, BigDecimal amount) {

        Account account = getAccountById(accountId);

        account.deposit(amount);

        ledgerService.recordDeposit(accountId, amount);

        transactionService.register(
                new Transaction(
                        account.getId(),
                        TransactionType.DEPOSIT,
                        amount,
                        "Deposit",
                        null,
                        null
                )
        );
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {

        Account account = getAccountById(accountId);

        account.withdraw(amount);

        ledgerService.recordWithdraw(accountId, amount);

        transactionService.register(
                new Transaction(
                        account.getId(),
                        TransactionType.WITHDRAW,
                        amount,
                        "Withdraw",
                        null,
                        null
                )
        );
    }

    @Override
    public void transfer(
            Long fromAccountId,
            Long toAccountId,
            BigDecimal amount,
            String transactionGroupId,
            String idempotencyKey) {

        if (idempotencyKey != null && idempotencyRepository.existsById(idempotencyKey)) {
                return;
        }

        if (idempotencyKey != null) {
                idempotencyRepository.save(
                        new IdempotencyKey(idempotencyKey, "TRANSFER")
                );
        }

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        Account fromAccount = accountRepository.findWithLockById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));

        Account toAccount = accountRepository.findWithLockById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException(toAccountId));

        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch between accounts");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        ledgerService.recordTransfer(fromAccountId, toAccountId, amount);

        if (transactionGroupId == null) {
            transactionGroupId = UUID.randomUUID().toString();
        }

        transactionService.register(
                new Transaction(
                        fromAccount.getId(),
                        TransactionType.TRANSFER_OUT,
                        amount,
                        "Transfer to account " + toAccountId,
                        toAccountId,
                        transactionGroupId
                )
        );

        transactionService.register(
                new Transaction(
                        toAccount.getId(),
                        TransactionType.TRANSFER_IN,
                        amount,
                        "Transfer from account " + fromAccountId,
                        fromAccountId,
                        transactionGroupId
                )
        );

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.block();
    }

    @Override
    public void activateAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.activate();
    }

    @Override
    public void closedAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.close();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
}