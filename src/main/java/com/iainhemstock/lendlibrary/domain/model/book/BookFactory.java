package com.iainhemstock.lendlibrary.domain.model.book;

public class BookFactory {

    public Book create(String id, String isbn, String title, String subtitle,
                       String author, String publisher, int yearPublished, int pageCount,
                       String description) {

        return new Book(
                new BookId(id),
                new Isbn(isbn),
                new Title(title),
                new Subtitle(subtitle),
                new Author(author),
                new Publisher(publisher),
                new Year(yearPublished),
                new PageCount(pageCount),
                new Description(description));
    }
}
