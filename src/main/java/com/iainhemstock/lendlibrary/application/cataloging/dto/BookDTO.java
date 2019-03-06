package com.iainhemstock.lendlibrary.application.cataloging.dto;

import java.util.Objects;

public class BookDTO {

    private final String bookId;
    private final String isbn;
    private final String title;
    private final String subtitle;
    private final int authorId;
    private final int publisherId;
    private final int yearPublished;
    private final int pageCount;
    private final String description;

    public BookDTO(String bookId,
                   String isbn,
                   String title,
                   String subtitle,
                   int authorId,
                   int publisherId,
                   int yearPublished,
                   int pageCount,
                   String description) {

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

    public String getId() {
        return this.bookId;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public int getPublisherId() {
        return this.publisherId;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId='" + bookId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", authorId=" + authorId +
                ", publisherId=" + publisherId +
                ", yearPublished=" + yearPublished +
                ", pageCount=" + pageCount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null /*|| getClass() != o.getClass()*/)
            return false;
        BookDTO bookDTO = (BookDTO) o;
        return authorId == bookDTO.authorId &&
                publisherId == bookDTO.publisherId &&
                yearPublished == bookDTO.yearPublished &&
                pageCount == bookDTO.pageCount &&
                Objects.equals(bookId, bookDTO.bookId) &&
                Objects.equals(isbn, bookDTO.isbn) &&
                Objects.equals(title, bookDTO.title) &&
                Objects.equals(subtitle, bookDTO.subtitle) &&
                Objects.equals(description, bookDTO.description);
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
