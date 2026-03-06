package com.corebank.api.corebank.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.corebank.api.corebank.domain.model.Transaction;
import com.corebank.api.corebank.web.dto.transactions.TransactionResponse;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getRelatedAccountId(),
                transaction.getCreatedAt()
        );
    }

    public List<TransactionResponse> toResponseList(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::toResponse)
                .toList();
    }

}