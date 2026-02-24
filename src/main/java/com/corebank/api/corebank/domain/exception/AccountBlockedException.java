package com.corebank.api.corebank.domain.exception;

public class AccountBlockedException extends RuntimeException {

    public AccountBlockedException() {
        super("The account is blocked.");
    }

}
