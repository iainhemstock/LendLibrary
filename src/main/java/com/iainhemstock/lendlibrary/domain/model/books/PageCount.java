package com.iainhemstock.lendlibrary.domain.model.books;

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
}
