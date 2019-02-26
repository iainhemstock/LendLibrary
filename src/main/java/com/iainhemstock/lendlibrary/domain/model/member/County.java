package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

public final class County {

    private final String county;

    public County(final String county) {
        this.county = county;
        Objects.requireNonNull(this.county, "argument was null");
        if (this.county.isEmpty()) throw new IllegalArgumentException("county cannot be empty");
    }

    @Override
    public String toString() {
        return this.county;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        County county1 = (County) o;
        return Objects.equals(county, county1.county);
    }

    @Override
    public int hashCode() {
        return Objects.hash(county);
    }
}
