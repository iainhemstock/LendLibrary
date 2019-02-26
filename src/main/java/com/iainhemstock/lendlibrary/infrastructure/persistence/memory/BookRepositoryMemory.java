package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.book.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryMemory extends BookRepository {

    List<Book> books = new ArrayList<>();

    @Override
    public boolean contains(final Book book) {
        return books.contains(book);
    }

    @Override
    public void add(final Book book) {
        books.add(book);
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Book getById(final BookId bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with id <" + bookId.toString() + "> could not be found"));
    }

    @Override
    public void update(Book book) {
        throw new UpdateBookException("Book is immutable and cannot be updated");
    }
}
