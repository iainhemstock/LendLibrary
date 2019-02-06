package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class Address2 {

    private final String address2;

    public Address2(final String address2) {
        this.address2 = address2;
        Objects.requireNonNull(this.address2, "argument was null");
    }

    @Override
    public String toString() {
        return this.address2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address2 address21 = (Address2) o;
        return Objects.equals(address2, address21.address2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address2);
    }
}
