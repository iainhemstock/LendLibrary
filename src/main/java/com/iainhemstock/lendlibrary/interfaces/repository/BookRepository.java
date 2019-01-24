package com.iainhemstock.lendlibrary.interfaces.repository;

import com.iainhemstock.lendlibrary.domain.books.entities.Book;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;

import java.util.List;

public interface BookRepository {
    BookId nextId();
    boolean contains(Book book);
    void add(Book book);
    List<Book> getAll();
    Book getById(BookId bookId);
    int size();
}
