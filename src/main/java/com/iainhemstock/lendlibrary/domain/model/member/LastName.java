package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

public final class LastName {

    private final String name;

    public LastName(final String name) {
        this.name = name;
        Objects.requireNonNull(this.name, "argument was null");
        if (this.name.isEmpty()) throw new IllegalArgumentException("last name cannot be empty");
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastName lastName = (LastName) o;
        return Objects.equals(name, lastName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
