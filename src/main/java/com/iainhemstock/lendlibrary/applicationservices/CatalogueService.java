package com.iainhemstock.lendlibrary.applicationservices;

import com.iainhemstock.lendlibrary.domain.model.books.Book;
import com.iainhemstock.lendlibrary.domain.model.books.BookId;

import java.util.List;

public interface CatalogueService {
    void addBook(final Book book);
    List<Book> getAllBooks();
    Book getBook(BookId bookId);
}
