package com.iainhemstock.lendlibrary.domain.model.book;

import com.iainhemstock.lendlibrary.domain.shared.Id;

public class BookFactory {

    public Book create(String id, String isbn, String title, String subtitle,
                       int authorId, int publisherId, int yearPublished, int pageCount,
                       String description) {

        return new Book(
                new BookId(id),
                new Isbn(isbn),
                new Title(title),
                new Subtitle(subtitle),
                new AuthorId(authorId),
                new PublisherId(publisherId),
                new Year(yearPublished),
                new PageCount(pageCount),
                new Description(description));
    }
}
