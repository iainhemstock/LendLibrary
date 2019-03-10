package com.iainhemstock.lendlibrary.application.cataloging;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.dto.CleanArchitectureBookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.impls.CatalogingServiceImpl;
import com.iainhemstock.lendlibrary.domain.model.book.BookFactory;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.BookRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AddBookToCatalogue {

    private CatalogingService catalogingService;
    private TestableBookRepository testableBookRepository;
    private BookFactory bookFactory;

    @Before
    public void setUp() {
        testableBookRepository = new TestableBookRepository();
        bookFactory = new BookFactory();
        catalogingService = new CatalogingServiceImpl(testableBookRepository, bookFactory);
    }

    @Test
    public void add_new_book_to_catalogue() {
        BookId bookId = testableBookRepository.nextId();
        BookDTO newBookDTO = new CleanArchitectureBookDTO(bookId.toString());

        String fetchedBookId = catalogingService.addBookToCatalog(newBookDTO);
        BookDTO fetchedBookDTO = catalogingService.fetchBook(fetchedBookId);
        List<BookDTO> allBookDTOs = catalogingService.fetchAllBooks();

        assertThat(fetchedBookId,
                is(equalTo(testableBookRepository.getLastId().toString())));

        assertThat(fetchedBookDTO.getId(),
                is(equalTo(fetchedBookId)));

        assertThat((fetchedBookDTO.getIsbn()),
                is(equalTo(newBookDTO.getIsbn())));

        assertThat((fetchedBookDTO.getTitle()),
                is(equalTo(newBookDTO.getTitle())));

        assertThat((fetchedBookDTO.getSubtitle()),
                is(equalTo(newBookDTO.getSubtitle())));

        assertThat((fetchedBookDTO.getAuthorId()),
                is(equalTo(newBookDTO.getAuthorId())));

        assertThat((fetchedBookDTO.getPublisherId()),
                is(equalTo(newBookDTO.getPublisherId())));

        assertThat((fetchedBookDTO.getYearPublished()),
                is(equalTo(newBookDTO.getYearPublished())));

        assertThat((fetchedBookDTO.getPageCount()),
                is(equalTo(newBookDTO.getPageCount())));

        assertThat((fetchedBookDTO.getDescription()),
                is(equalTo(newBookDTO.getDescription())));

        assertThat(allBookDTOs.get(0),
                is(equalTo(fetchedBookDTO)));
    }

    private class TestableBookRepository extends BookRepositoryMemory {
        private BookId bookId;

        @Override
        public BookId nextId() {
            return bookId = new BookId(UUID.randomUUID().toString().replace("-", ""));
        }

        public BookId getLastId() {
            return bookId;
        }
    }
}
