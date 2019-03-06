package com.iainhemstock.lendlibrary.domain.model.loan;

public final class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
