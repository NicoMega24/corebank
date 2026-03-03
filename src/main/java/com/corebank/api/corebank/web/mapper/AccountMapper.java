package com.corebank.api.corebank.web.mapper;

import org.springframework.stereotype.Component;

import com.corebank.api.corebank.domain.model.Account;
import com.corebank.api.corebank.web.dto.account.AccountResponseDTO;

@Component
public class AccountMapper {

    public AccountResponseDTO toResponse(Account account) {
        return new AccountResponseDTO(
            account.getId(),
            account.getAccountNumber(),
            account.getCustomerId(),
            account.getBalance(),
            account.getCurrency(),
            account.getAccountType(),
            account.getStatus(),
            account.getCreatedAt()
        );
    }
}
