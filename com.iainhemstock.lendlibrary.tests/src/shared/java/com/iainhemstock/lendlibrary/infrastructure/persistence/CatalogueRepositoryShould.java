package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.catalogue.Book;
import com.iainhemstock.lendlibrary.domain.model.catalogue.BookId;
import com.iainhemstock.lendlibrary.domain.model.catalogue.CatalogueRepository;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public abstract class CatalogueRepositoryShould extends RepositoryShould<
        CatalogueRepository,
        Book,
        BookId> {

    @Override
    protected Book makeRepositoryItem() {
        Book book = Mockito.mock(Book.class);
        BookId bookId = Mockito.mock(BookId.class);
        when(book.getId()).thenReturn(bookId);
        return book;
    }
}
