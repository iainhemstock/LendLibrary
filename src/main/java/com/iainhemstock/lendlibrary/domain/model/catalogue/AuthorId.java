package com.iainhemstock.lendlibrary.domain.model.catalogue;

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
}
