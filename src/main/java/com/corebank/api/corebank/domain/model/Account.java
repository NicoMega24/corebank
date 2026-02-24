package com.corebank.api.corebank.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.corebank.api.corebank.domain.enums.AccountStatusEnum;
import com.corebank.api.corebank.domain.enums.AccountTypeEnum;
import com.corebank.api.corebank.domain.enums.CurrencyEnum;
import com.corebank.api.corebank.domain.exception.AccountBlockedException;
import com.corebank.api.corebank.domain.exception.AccountClosedException;
import com.corebank.api.corebank.domain.exception.InsufficientFundsException;
import com.corebank.api.corebank.domain.exception.InvalidAccountStateException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable= false)
    private String accountNumber;

    @Column(nullable= false)
    private Long customerId;

    @Column(nullable= false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false)
    private CurrencyEnum currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false)
    private AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false)
    private AccountStatusEnum status;

    @Column(nullable= false)
    private LocalDateTime createdAt;

    public Account(String accountNumber, Long customerId, CurrencyEnum currency, AccountTypeEnum accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = BigDecimal.ZERO;
        this.currency = currency;
        this.accountType = accountType;
        this.status = AccountStatusEnum.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    protected Account(){
        
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public AccountStatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        validateAccountOperable();

        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        validateAccountOperable();

        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        this.balance = this.balance.subtract(amount);
    }

    public void block() {
        if (this.status == AccountStatusEnum.BLOCKED) {
            throw new InvalidAccountStateException("Account is already blocked");
        }

        if (this.status == AccountStatusEnum.CLOSED) {
            throw new AccountClosedException();
        }

        this.status = AccountStatusEnum.BLOCKED;
    }

    public void activate() {
        if (this.status == AccountStatusEnum.ACTIVE) {
            throw new InvalidAccountStateException("Account is already active");
        }

        if (this.status == AccountStatusEnum.CLOSED) {
            throw new AccountClosedException();
        }

        this.status = AccountStatusEnum.ACTIVE;
    }

    public void close() {
        if (this.status == AccountStatusEnum.CLOSED) {
            throw new InvalidAccountStateException("Account is already closed");
        }

        if (this.balance.compareTo(BigDecimal.ZERO) > 0) {
            throw new InvalidAccountStateException("Cannot close account with remaining balance");
        }

        this.status = AccountStatusEnum.CLOSED;
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAccountStateException("Amount must be greater than zero");
        }
    }

    private void validateAccountOperable() {
        if (this.status == AccountStatusEnum.CLOSED) {
            throw new AccountClosedException();
        }

        if (this.status == AccountStatusEnum.BLOCKED) {
            throw  new AccountBlockedException();
        }
    }

}
