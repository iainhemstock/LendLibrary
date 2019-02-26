package com.iainhemstock.lendlibrary.domain.model.book;

import java.util.Objects;

public final class AuthorId {

    private final int id;

    public AuthorId(final int id) {
        this.id = id;
        if (this.id < 1) throw new IllegalArgumentException("id cannot be zero or negative");
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorId authorId = (AuthorId) o;
        return id == authorId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
