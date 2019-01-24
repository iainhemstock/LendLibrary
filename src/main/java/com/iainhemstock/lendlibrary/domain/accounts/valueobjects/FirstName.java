package com.iainhemstock.lendlibrary.domain.accounts.valueobjects;

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
}
