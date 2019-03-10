package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.BookRepositoryShould;

public class BookRepositoryMemoryShould extends BookRepositoryShould {

    private BookRepository bookRepository = new BookRepositoryMemory();

    @Override
    protected BookRepository getRepository() {
        return bookRepository;
    }

    // todo: write a test ensures the book repo stores a copy of book and not a reference
}
