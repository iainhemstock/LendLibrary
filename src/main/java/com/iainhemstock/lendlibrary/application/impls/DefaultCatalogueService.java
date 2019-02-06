package com.iainhemstock.lendlibrary.application.impls;

import com.iainhemstock.lendlibrary.application.CatalogueService;
import com.iainhemstock.lendlibrary.domain.model.catalogue.Book;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;
import com.iainhemstock.lendlibrary.domain.shared.RepositoryException;

import java.util.List;
import java.util.Objects;

public class DefaultCatalogueService implements CatalogueService {

    private CatalogueRepository catalogueRepository;

    public DefaultCatalogueService(final CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
        Objects.requireNonNull(this.catalogueRepository, "Catalogue repository is required");
    }

    @Override
    public void addBook(final Book book) {
        Objects.requireNonNull(book, "Book profile is required");
        try {
            catalogueRepository.add(book);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return catalogueRepository.getAll();
    }

    @Override
    public Book getBook(final BookId bookId) {
        Objects.requireNonNull(bookId, "Book id is required");
        Book book = null;
        try {
            book = catalogueRepository.getById(bookId);
        }
        catch (RepositoryException ex) {
            throw ex;
        }
        return book;
    }
}
