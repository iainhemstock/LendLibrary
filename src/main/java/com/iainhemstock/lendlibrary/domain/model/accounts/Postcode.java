package com.iainhemstock.lendlibrary.domain.model.accounts;

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
}
