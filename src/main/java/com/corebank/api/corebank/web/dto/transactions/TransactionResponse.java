package com.corebank.api.corebank.web.dto.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.corebank.api.corebank.domain.enums.TransactionType;

public record TransactionResponse(
            Long id,
            Long accountId,
            TransactionType type,
            BigDecimal amount,
            String description,
            Long relatedAccountId,
            LocalDateTime createdAt
) {

}
