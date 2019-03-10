package com.iainhemstock.lendlibrary.domain.model.book;

public final class Author {
    private final String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return this.author;
    }
}
