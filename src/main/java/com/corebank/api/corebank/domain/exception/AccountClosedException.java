package com.corebank.api.corebank.domain.exception;

public class AccountClosedException extends RuntimeException{

    public AccountClosedException() {
        super("The account is closed and cannot perfom operations.");
    }

}
