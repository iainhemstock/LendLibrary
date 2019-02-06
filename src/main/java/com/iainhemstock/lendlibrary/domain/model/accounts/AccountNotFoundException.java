package com.iainhemstock.lendlibrary.domain.model.accounts;

public final class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
