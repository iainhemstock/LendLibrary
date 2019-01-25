package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class Telephone {

    private final String telephone;

    public Telephone(final String telephone) {
        this.telephone = telephone;
        Objects.requireNonNull(this.telephone, "argument was null");
        if (telephone.isEmpty()) throw new IllegalArgumentException("telephone cannot be empty");
    }

    @Override
    public String toString() {
        return this.telephone;
    }
}
