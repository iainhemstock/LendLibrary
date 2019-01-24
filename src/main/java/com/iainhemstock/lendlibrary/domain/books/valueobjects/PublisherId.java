package com.iainhemstock.lendlibrary.domain.books.valueobjects;

public final class PublisherId {

    private final int id;

    public PublisherId(final int id) {
        this.id = id;
        if (this.id < 1) throw new IllegalArgumentException("id cannot be zero or negative");
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
