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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone1 = (Telephone) o;
        return Objects.equals(telephone, telephone1.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone);
    }
}
