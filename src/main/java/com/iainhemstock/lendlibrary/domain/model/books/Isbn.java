package com.iainhemstock.lendlibrary.domain.model.books;

import java.util.Objects;

public final class Isbn {

    private final String isbn;

    public Isbn(final String isbn) {
        this.isbn = isbn;
        Objects.requireNonNull(this.isbn, "argument was null");
        if (!this.isbn.matches("^(([0-9]{10})|([0-9]{13}))$"))
            throw new IllegalArgumentException("isbn must be 10 or 13 digits only");
    }

    @Override
    public String toString() {
        return this.isbn;
    }
}
