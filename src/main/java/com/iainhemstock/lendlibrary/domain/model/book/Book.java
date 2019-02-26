package com.iainhemstock.lendlibrary.domain.model.book;

import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Objects;

public class Book implements Entity {

    private final BookId bookId;
    private final Isbn isbn;
    private final Title title;
    private final Subtitle subtitle;
    private final AuthorId authorId;
    private final PublisherId publisherId;
    private final Year yearPublished;
    private final PageCount pageCount;
    private final Description description;

    public Book(final BookId bookId,
                final Isbn isbn,
                final Title title,
                final Subtitle subtitle,
                final AuthorId authorId,
                final PublisherId publisherId,
                final Year yearPublished,
                final PageCount pageCount,
                final Description description) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.authorId = authorId;
        this.publisherId = publisherId;
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

    public AuthorId getAuthorId() {
        return authorId;
    }

    public PublisherId getPublisherId() {
        return publisherId;
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
                ", authorId=" + authorId +
                ", publisherId=" + publisherId +
                ", yearPublished=" + yearPublished +
                ", pageCount=" + pageCount +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null /*|| getClass() != o.getClass()*/)
            return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(subtitle, book.subtitle) &&
                Objects.equals(authorId, book.authorId) &&
                Objects.equals(publisherId, book.publisherId) &&
                Objects.equals(yearPublished, book.yearPublished) &&
                Objects.equals(pageCount, book.pageCount) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                bookId,
                isbn,
                title,
                subtitle,
                authorId,
                publisherId,
                yearPublished,
                pageCount,
                description);
    }
}
