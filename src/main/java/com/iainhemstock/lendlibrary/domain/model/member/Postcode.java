package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

public final class Postcode {

    private final String postcode;

    public Postcode(final String postcode) {
        this.postcode = postcode;
        Objects.requireNonNull(this.postcode, "argument was null");
        if (this.postcode.isEmpty()) throw new IllegalArgumentException("postcode cannot be empty");
    }

    @Override
    public String toString() {
        return this.postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Postcode postcode1 = (Postcode) o;
        return Objects.equals(postcode, postcode1.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postcode);
    }
}
