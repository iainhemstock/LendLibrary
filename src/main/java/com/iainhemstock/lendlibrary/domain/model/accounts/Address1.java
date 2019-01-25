package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class Address1 {

    private final String address1;

    public Address1(final String address1) {
        this.address1 = address1;
        Objects.requireNonNull(this.address1, "argument was null");
        if (this.address1.isEmpty()) throw new IllegalArgumentException("address1 cannot be empty");
    }

    @Override
    public String toString() {
        return this.address1;
    }
}
