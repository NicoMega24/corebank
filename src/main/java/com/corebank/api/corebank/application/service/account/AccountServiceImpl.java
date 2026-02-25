package com.corebank.api.corebank.application.service.account;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.corebank.api.corebank.application.exceptions.AccountNotFoundException;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.infrastructure.persistence.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.withdraw(amount);
        accountRepository.save(account);
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.block();
        accountRepository.save(account);
    }

    @Override
    public void activateAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.activate();
        accountRepository.save(account);
    }

    @Override
    public void closedAccount(Long accountId) {
        Account account = getAccountById(accountId);
        account.close();
        accountRepository.save(account);
        
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

}
