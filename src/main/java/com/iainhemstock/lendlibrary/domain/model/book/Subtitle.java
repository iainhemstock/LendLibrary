package com.iainhemstock.lendlibrary.domain.model.book;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subtitle subtitle1 = (Subtitle) o;
        return Objects.equals(subtitle, subtitle1.subtitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtitle);
    }
}
