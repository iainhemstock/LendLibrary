package com.iainhemstock.lendlibrary.domain.model.book;

import java.util.Objects;

public final class Year {

    private final int year;

    public Year(final int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.valueOf(this.year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year1 = (Year) o;
        return year == year1.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }
}
