package com.iainhemstock.lendlibrary.domain.model.accounts;

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
}
