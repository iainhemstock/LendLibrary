package com.iainhemstock.lendlibrary.domain.model.book;

public final class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String msg) {
        super(msg);
    }
}
