package com.iainhemstock.lendlibrary.domain.model.book;

import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Objects;

public class Book implements Entity {

    private final BookId bookId;
    private final Isbn isbn;
    private final Title title;
    private final Subtitle subtitle;
    private final Author author;
    private final Publisher publisher;
    private final Year yearPublished;
    private final PageCount pageCount;
    private final Description description;

    public Book(final BookId bookId,
                final Isbn isbn,
                final Title title,
                final Subtitle subtitle,
                final Author author,
                final Publisher publisher,
                final Year yearPublished,
                final PageCount pageCount,
                final Description description) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.pageCount = pageCount;
        this.description = description;
    }

    @Override
    public BookId getId() {
        return this.bookId;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Title getTitle() {
        return title;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public Author getAuthor() {
        return this.author;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public Year getYearPublished() {
        return yearPublished;
    }

    public PageCount getPageCount() {
        return pageCount;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn=" + isbn +
                ", title=" + title +
                ", subtitle=" + subtitle +
                ", author=" + author +
                ", publisher=" + publisher +
                ", yearPublished=" + yearPublished +
                ", pageCount=" + pageCount +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return Objects.equals(bookId, ((Book) o).bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}
