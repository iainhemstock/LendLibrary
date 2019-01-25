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
}
