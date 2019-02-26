package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

public final class AddressLine2 {

    private final String addressLine2;

    public AddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
        Objects.requireNonNull(this.addressLine2, "Address line 2 is required");
    }

    @Override
    public String toString() {
        return this.addressLine2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressLine2 otherAddressLine2 = (AddressLine2) o;
        return Objects.equals(addressLine2, otherAddressLine2.addressLine2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine2);
    }
}
