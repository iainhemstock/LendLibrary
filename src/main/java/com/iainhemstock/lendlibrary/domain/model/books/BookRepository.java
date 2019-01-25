package com.iainhemstock.lendlibrary.domain.model.books;

import java.util.List;

public interface BookRepository {
    BookId nextId();
    boolean contains(Book book);
    void add(Book book);
    List<Book> getAll();
    Book getById(BookId bookId);
    int size();
}
