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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address1 address11 = (Address1) o;
        return Objects.equals(address1, address11.address1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address1);
    }
}
