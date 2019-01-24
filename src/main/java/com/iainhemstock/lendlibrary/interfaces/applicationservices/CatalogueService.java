package com.iainhemstock.lendlibrary.interfaces.applicationservices;

import com.iainhemstock.lendlibrary.domain.books.entities.Book;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;

import java.util.List;

public interface CatalogueService {
    void addBook(final Book book);
    List<Book> getAllBooks();
    Book getBook(BookId bookId);
}
