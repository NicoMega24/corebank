package com.corebank.api.corebank.application.service.account;

import java.math.BigDecimal;

import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.model.Account;

public interface AccountService {

    Account createAccount(String accountNumber, 
                        Long customerId, 
                        CurrencyEnum currency, 
                        AccountTypeEnum accountType);

    Account getAccountById(Long id);

    void deposit(Long accountId, BigDecimal amount);

    void withdraw(Long accountId, BigDecimal amount);

    void blockAccount(Long accountId);

    void activateAccount(Long accountId);

    void closedAccount(Long accountId);

}
