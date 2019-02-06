package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.catalogue.Book;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;

import java.util.*;

public class CatalogueRepositoryMemory extends CatalogueRepository {

    List<Book> books;

    public CatalogueRepositoryMemory() {
        this.books = new ArrayList<>();
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
            if (Objects.equals(book.getId(), bookId))
                return book;
        }
        throw new NullPointerException("book profile not found in repository");
    }
}
