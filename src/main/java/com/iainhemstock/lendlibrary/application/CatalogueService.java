package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.model.catalogue.Book;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;

import java.util.List;

public interface CatalogueService {
    void addBook(final Book book);
    List<Book> getAllBooks();
    Book getBook(BookId bookId);
}
