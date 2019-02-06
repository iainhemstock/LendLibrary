package com.iainhemstock.lendlibrary.domain.shared;

import java.util.Objects;

public abstract class BaseId {

    private final String id;

    public BaseId(final String id) {
        this.id = id;
        Objects.requireNonNull(this.id, "Argument was null");
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
        BaseId baseId = (BaseId) o;
        return Objects.equals(id, baseId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
