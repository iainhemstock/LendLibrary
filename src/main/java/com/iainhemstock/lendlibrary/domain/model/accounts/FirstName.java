package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class FirstName {

    private final String name;

    public FirstName(final String name) {
        this.name = name;
        Objects.requireNonNull(this.name, "argument is null");
        if (this.name.isEmpty()) throw new IllegalArgumentException("first name cannot be empty");
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstName firstName = (FirstName) o;
        return Objects.equals(name, firstName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
