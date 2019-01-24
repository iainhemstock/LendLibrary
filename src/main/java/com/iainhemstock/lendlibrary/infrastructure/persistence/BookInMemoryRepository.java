package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.books.entities.Book;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import com.iainhemstock.lendlibrary.interfaces.repository.BookRepository;

import java.util.*;

public class BookInMemoryRepository implements BookRepository {

    List<Book> books;

    public BookInMemoryRepository() {
        this.books = new ArrayList<>();
    }

    @Override
    public BookId nextId() {
        return new BookId(UUID.randomUUID().toString());
    }

    @Override
    public int size() {
        return books.size();
    }

    @Override
    public boolean contains(final Book book) {
        return books.contains(book);
    }

    @Override
    public void add(final Book book) {
        books.add(book);
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Book getById(final BookId bookId) {
        final Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (Objects.equals(book.getBookId(), bookId))
                return book;
        }
        throw new NullPointerException("book profile not found in repository");
    }
}
