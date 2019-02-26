package com.iainhemstock.lendlibrary.domain.model.member;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(city, city1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
}
