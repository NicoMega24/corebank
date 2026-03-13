package com.corebank.api.corebank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private BigDecimal amount;

    private BigDecimal interestRate;

    private Integer termMonths;

    private BigDecimal remainingBalance;

    private LocalDateTime createdAt;

    public Loan(Long customerId, BigDecimal amount, BigDecimal interestRate, Integer termMonths) {
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.remainingBalance = amount;
        this.createdAt = LocalDateTime.now();
    }

    public void makePayment(BigDecimal payment) {
        this.remainingBalance = this.remainingBalance.subtract(payment);
    }

}