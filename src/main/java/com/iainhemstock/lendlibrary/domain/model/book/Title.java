package com.iainhemstock.lendlibrary.domain.model.book;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
