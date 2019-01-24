package com.iainhemstock.lendlibrary.domain.accounts.valueobjects;

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
}
