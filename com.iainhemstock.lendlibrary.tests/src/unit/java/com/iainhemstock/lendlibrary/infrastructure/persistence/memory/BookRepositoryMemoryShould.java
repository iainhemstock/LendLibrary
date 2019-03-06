package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.application.domain.model.book.HeadFirstDesignPatternsBook;
import com.iainhemstock.lendlibrary.domain.model.book.Book;
import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.BookRepositoryShould;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookRepositoryMemoryShould extends BookRepositoryShould {

    private BookRepository bookRepository = new BookRepositoryMemory();

    @Override
    protected BookRepository getRepository() {
        return bookRepository;
    }

    // todo: write a test ensures the book repo stores a copy of book and not a reference
}
