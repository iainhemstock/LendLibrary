package com.iainhemstock.lendlibrary.domain.books.valueobjects;

import java.util.Objects;

public class Subtitle {

    private final String subtitle;

    public Subtitle(final String subtitle) {
        this.subtitle = subtitle;
        Objects.requireNonNull(this.subtitle, "argument was null");
    }

    @Override
    public String toString() {
        return this.subtitle;
    }
}
