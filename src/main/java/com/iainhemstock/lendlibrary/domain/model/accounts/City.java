package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class City {

    private final String city;

    public City(final String city) {
        this.city = city;
        Objects.requireNonNull(this.city, "argument was null");
        if (this.city.isEmpty()) throw new IllegalArgumentException("city cannot be empty");
    }

    @Override
    public String toString() {
        return this.city;
    }
}
