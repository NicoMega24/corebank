package com.corebank.api.corebank.web.dto.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.corebank.api.corebank.domain.enums.AccountStatusEnum;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;

public record  AccountResponseDTO(

    Long id,
    String accountNumber,
    Long customerId,
    BigDecimal balance,
    CurrencyEnum currency,
    AccountTypeEnum accountType,
    AccountStatusEnum status,
    LocalDateTime createdAt
){}
