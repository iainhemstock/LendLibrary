package com.iainhemstock.lendlibrary.infrastructure.persistence;

import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
import org.junit.Before;

public class BookRepositoryMemoryShould extends BookRepositoryShould {

    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMemory();
    }

    @Override
    protected BookRepository getRepository() {
        return bookRepository;
    }
}
