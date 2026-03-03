package com.corebank.api.corebank.web.dto.account;

import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AccountRequestDTO(

    @NotBlank(message= "Account number is required")
    String accountNumber,

    @NotNull(message= "Customer id is required")
    Long customerId,

    @NotNull(message= "Currency is required")
    CurrencyEnum currency,

    @NotNull(message= "Account type is required")
    AccountTypeEnum accountType
){}

