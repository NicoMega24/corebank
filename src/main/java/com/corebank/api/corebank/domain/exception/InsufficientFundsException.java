package com.corebank.api.corebank.domain.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("Insufficient funds this operation.");
    }
}
