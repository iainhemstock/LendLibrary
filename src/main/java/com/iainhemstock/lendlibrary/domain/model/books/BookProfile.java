package com.iainhemstock.lendlibrary.domain.model.books;

import java.util.Objects;

public final class BookProfile {

    private final Isbn isbn;
    private final Title title;
    private final Subtitle subtitle;
    private final AuthorId authorId;
    private final PublisherId publisherId;
    private final Year yearPublished;
    private final PageCount pageCount;
    private final Description description;

    public BookProfile(final Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.authorId = builder.authorId;
        this.publisherId = builder.publisherId;
        this.yearPublished = builder.yearPublished;
        this.pageCount = builder.pageCount;
        this.description = builder.description;
    }

    public Isbn getIsbn() {
        return this.isbn;
    }

    public Title getTitle() {
        return this.title;
    }

    public Subtitle getSubtitle() {
        return this.subtitle;
    }

    public AuthorId getAuthorId() {
        return this.authorId;
    }

    public PublisherId getPublisherId() {
        return this.publisherId;
    }

    public Year getYearPublished() {
        return this.yearPublished;
    }

    public PageCount getPageCount() {
        return this.pageCount;
    }

    public Description getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookProfile that = (BookProfile) o;
        return Objects.equals(isbn, that.isbn) &&
                Objects.equals(title, that.title) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(publisherId, that.publisherId) &&
                Objects.equals(yearPublished, that.yearPublished) &&
                Objects.equals(pageCount, that.pageCount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, subtitle, authorId, publisherId, yearPublished, pageCount, description);
    }

    public static final class Builder {

        private Isbn isbn;
        private Title title;
        private Subtitle subtitle;
        private AuthorId authorId;
        private PublisherId publisherId;
        private Year yearPublished;
        private PageCount pageCount;
        private Description description;

        public Builder withIsbn(final Isbn isbn) {
            this.isbn = isbn;
            Objects.requireNonNull(this.isbn, "Isbn is required");
            return this;
        }

        public Builder withTitle(final Title title) {
            this.title = title;
            Objects.requireNonNull(this.title, "Title is required");
            return this;
        }

        public Builder withSubtitle(final Subtitle subtitle) {
            this.subtitle = subtitle;
            Objects.requireNonNull(this.subtitle, "Subtitle is required");
            return this;
        }

        public Builder withAuthorId(final AuthorId authorId) {
            this.authorId = authorId;
            Objects.requireNonNull(this.authorId, "Author Id is required");
            return this;
        }

        public Builder withPublisherId(final PublisherId publisherId) {
            this.publisherId = publisherId;
            Objects.requireNonNull(this.publisherId, "Publisher Id is required");
            return this;
        }

        public Builder withYearPublished(final Year yearPublished) {
            this.yearPublished = yearPublished;
            Objects.requireNonNull(this.yearPublished, "Year is required");
            return this;
        }

        public Builder withPageCount(final PageCount pageCount) {
            this.pageCount = pageCount;
            Objects.requireNonNull(this.pageCount, "Page count is required");
            return this;
        }

        public Builder withDescription(final Description description) {
            this.description = description;
            Objects.requireNonNull(this.description, "Description is required");
            return this;
        }

        public BookProfile build() {
            return new BookProfile(this);
        }
    }
}
