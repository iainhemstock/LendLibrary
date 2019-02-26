package com.iainhemstock.lendlibrary.domain.model.book;

import java.util.Objects;

public final class PageCount {

    private final int pageCount;

    public PageCount(final int pageCount) {
        this.pageCount = pageCount;
        if (this.pageCount < 1) throw new IllegalArgumentException("page count cannot be less than 1");
    }

    @Override
    public String toString() {
        return String.valueOf(this.pageCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageCount pageCount1 = (PageCount) o;
        return pageCount == pageCount1.pageCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageCount);
    }
}
