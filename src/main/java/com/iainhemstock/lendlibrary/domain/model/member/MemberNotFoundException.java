package com.iainhemstock.lendlibrary.domain.model.member;

public final class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
