package com.iainhemstock.lendlibrary.application.cataloging;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.dto.CleanArchitectureBookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.impls.CatalogingServiceImpl;
import com.iainhemstock.lendlibrary.domain.model.book.BookFactory;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
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
    private BookRepository bookRepository;
    private BookFactory bookFactory;

    @Before
    public void setUp() {
        bookRepository = new BookRepositoryMemory();
        bookFactory = new BookFactory();
        catalogingService = new CatalogingServiceImpl(bookRepository, bookFactory);
    }

    @Test
    public void add_new_book_to_catalogue() {
        BookDTO sourceBookDTO = new CleanArchitectureBookDTO(null);

        String fetchedBookId = catalogingService.addBookToCatalog(sourceBookDTO);
        BookDTO fetchedBookDTO = catalogingService.fetchBook(fetchedBookId);
        List<BookDTO> allBookDTOs = catalogingService.fetchAllBooks();

        assertThat(fetchedBookDTO,
                is(equalTo(new CleanArchitectureBookDTO(fetchedBookId))));

        assertThat(allBookDTOs.get(0),
                is(equalTo(fetchedBookDTO)));
    }
}
