package com.iainhemstock.lendlibrary.domain.model.book;

public final class Publisher {
    private final String publisher;

    public Publisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return this.publisher;
    }
}
