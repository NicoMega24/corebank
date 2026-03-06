package com.corebank.api.corebank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.corebank.api.corebank.domain.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "transactions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private String description;

    private Long relatedAccountId;

    public Transaction(Long accountId,
                        TransactionType type,
                        BigDecimal amount,
                        String description,
                        Long relatedAccountId) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.description = description;
        this.relatedAccountId = relatedAccountId;
        }

}
