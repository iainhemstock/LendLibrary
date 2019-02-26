package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.book.Book;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.book.CatalogueRepository;
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
