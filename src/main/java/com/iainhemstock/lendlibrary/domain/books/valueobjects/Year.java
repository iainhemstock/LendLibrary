package com.iainhemstock.lendlibrary.domain.books.valueobjects;

public final class Year {

    private final int year;

    public Year(final int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.valueOf(this.year);
    }
}
