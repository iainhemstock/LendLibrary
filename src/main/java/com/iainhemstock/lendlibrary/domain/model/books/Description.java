package com.iainhemstock.lendlibrary.domain.model.books;

public final class Description {

    private final String description;

    public Description(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
