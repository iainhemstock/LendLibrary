package com.iainhemstock.lendlibrary.domain.books.valueobjects;

import java.util.Objects;

public final class Title {

    private final String title;

    public Title(final String title) {
        this.title = title;
        Objects.requireNonNull(this.title, "argument was null");
        if (this.title.isEmpty()) throw new IllegalArgumentException("title cannot be empty");
    }

    @Override
    public String toString() {
        return this.title;
    }
}
