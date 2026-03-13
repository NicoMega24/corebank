package com.corebank.api.corebank.web.dto.account;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferRequestDTO(

        @NotNull
        Long fromAccountId,

        @NotNull
        Long toAccountId,

        @NotNull
        @Positive
        BigDecimal amount
) {
}
