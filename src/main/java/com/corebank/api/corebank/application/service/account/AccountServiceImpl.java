package com.corebank.api.corebank.application.service.account;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corebank.api.corebank.application.exceptions.AccountNotFoundException;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.infrastructure.persistence.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(String accountNumber, 
                                Long customerId,
                                CurrencyEnum currency, 
                                AccountTypeEnum accountType) {
        
        Account account = new Account(accountNumber, customerId, currency, accountType);

        return accountRepository.save(account);
    }

    @Override
    public void deposit(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.deposit(amount);
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.withdraw(amount);
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
