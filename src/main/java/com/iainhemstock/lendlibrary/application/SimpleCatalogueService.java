package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.domain.books.entities.Book;
import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import com.iainhemstock.lendlibrary.interfaces.repository.BookRepository;
import com.iainhemstock.lendlibrary.interfaces.repository.RepositoryException;
import com.iainhemstock.lendlibrary.interfaces.applicationservices.CatalogueService;

import java.util.List;
import java.util.Objects;

public class SimpleCatalogueService implements CatalogueService {

    private BookRepository bookRepository;

    public SimpleCatalogueService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        Objects.requireNonNull(this.bookRepository, "Book Repository is required");
    }

    @Override
    public void addBook(final Book book) {
        Objects.requireNonNull(book, "Book Profile is required");
        try {
            bookRepository.add(book);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book getBook(final BookId bookId) {
        Objects.requireNonNull(bookId, "Book Id is required");
        Book book = null;
        try {
            book = bookRepository.getById(bookId);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
        return book;
    }
}
