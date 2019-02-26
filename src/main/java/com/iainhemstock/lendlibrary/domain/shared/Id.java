package com.iainhemstock.lendlibrary.domain.shared;

import java.util.Objects;

public class Id {

    private final String id;

    public Id(final String id) {
        this.id = id;
        Objects.requireNonNull(this.id, "Id is required");
        if (this.id.equals(""))
            throw new IllegalArgumentException("Id cannot be empty");
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return Objects.equals(this.id, id.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
