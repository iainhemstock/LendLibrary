package com.iainhemstock.lendlibrary.domain.books.entities;

import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;

import java.util.Objects;

public final class Book {
    private final BookId bookId;
    private final BookProfile bookProfile;

    public Book(final BookId bookId, final BookProfile bookProfile) {
        this.bookId = bookId;
        this.bookProfile = bookProfile;
    }

    public BookId getBookId() {
        return this.bookId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId.equals(book.bookId) &&
                bookProfile.equals(book.bookProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookProfile);
    }
}
