package com.corebank.api.corebank.web.dto.account;

import java.math.BigDecimal;

public record TransferRequestDTO(
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount
) {
}
