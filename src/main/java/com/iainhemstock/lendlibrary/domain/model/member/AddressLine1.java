package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

public final class AddressLine1 {

    private final String addressLine1;

    public AddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
        Objects.requireNonNull(this.addressLine1, "Address line 1 is required");
        if (this.addressLine1.isEmpty()) throw new IllegalArgumentException("Address line 1 cannot be blank");
    }

    @Override
    public String toString() {
        return this.addressLine1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressLine1 otherAddressLine1 = (AddressLine1) o;
        return Objects.equals(addressLine1, otherAddressLine1.addressLine1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1);
    }
}
