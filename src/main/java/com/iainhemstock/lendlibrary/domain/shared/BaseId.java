package com.iainhemstock.lendlibrary.domain.shared;

import java.util.Objects;

public class BaseId {

    private final String id;

    public BaseId(final String id) {
        this.id = id;
        Objects.requireNonNull(this.id, "argument was null");
        if (this.id.equals(""))
            throw new IllegalArgumentException("id cannot be empty");
    }

    @Override
    public String toString() {
        return this.id;
    }
}
