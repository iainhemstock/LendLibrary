package com.iainhemstock.lendlibrary.application.cataloging;

import com.iainhemstock.lendlibrary.application.cataloging.dto.BookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.dto.DomainDrivenDesignBookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.dto.HeadFirstDesignPatternsBookDTO;
import com.iainhemstock.lendlibrary.application.cataloging.impls.CatalogingServiceImpl;
import com.iainhemstock.lendlibrary.application.cataloging.impls.assembler.BookDTOAssembler;
import com.iainhemstock.lendlibrary.application.domain.model.book.DomainDrivenDesignBook;
import com.iainhemstock.lendlibrary.application.domain.model.book.HeadFirstDesignPatternsBook;
import com.iainhemstock.lendlibrary.domain.model.book.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.BookRepositoryMemory;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CatalogingServiceShould {

    @Test
    public void throw_if_books_service_is_initialized_with_null_book_repository() {
        try {
            new CatalogingServiceImpl(
                    null, new BookFactory());
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book repository is required")));
        }
    }

    @Test
    public void throw_if_books_service_is_initialized_with_null_book_factory() {
        try {
            new CatalogingServiceImpl(
                    mock(BookRepository.class), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book factory is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_save_null_book() {
        try {
            new CatalogingServiceImpl(
                    mock(BookRepository.class), new BookFactory())
                        .addBookToCatalog(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book DTO is required")));
        }
    }

    @Test
    public void save_book_in_repo_and_return_book_id() {
        BookId bookId = new BookId("id-1234");
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.nextId())
                .thenReturn(bookId);

        CatalogingService catalogingService = new CatalogingServiceImpl(
                bookRepository, new BookFactory());

        String addedBookId = catalogingService.addBookToCatalog(
                new HeadFirstDesignPatternsBookDTO(null));

        assertThat(addedBookId,
                is(equalTo(bookId.toString())));

        verify(bookRepository)
                .add(new HeadFirstDesignPatternsBook(bookId.toString()));
    }

    @Test
    public void throw_when_trying_to_fetch_book_with_null_id() {
        try {
            new CatalogingServiceImpl(
                    mock(BookRepository.class), new BookFactory())
                        .fetchBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(Matchers.equalTo("Book id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_fetch_book_that_does_not_exist() {
        String absentBookId = "id-2345";

        BookRepository bookRepository = mock(BookRepository.class);
        doThrow(new BookNotFoundException("Book with id <" + absentBookId + "> not found"))
                .when(bookRepository).getById(any(BookId.class));

        try {
            new CatalogingServiceImpl(bookRepository, new BookFactory())
                    .fetchBook(absentBookId);
            fail("expected method under test to throw BookNotFoundException but it didn't");
        } catch (BookNotFoundException ex) {
            assertThat(ex.getMessage(),
                    is(equalTo("Book with id <" + absentBookId + "> not found")));
        }
    }

    @Test
    public void return_details_of_given_book() {
        BookId bookId = new BookId("id-1234");

        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.getById(bookId))
                .thenReturn(new HeadFirstDesignPatternsBook(bookId.toString()));

        BookDTO fetchedBookDTO =
                new CatalogingServiceImpl(bookRepository, new BookFactory())
                        .fetchBook(bookId.toString());

        assertThat(fetchedBookDTO,
                is(equalTo(new HeadFirstDesignPatternsBookDTO(bookId.toString()))));
    }

    @Test
    public void return_empty_list_when_trying_to_fetch_books_that_dont_exist() {
        CatalogingService catalogingService = new CatalogingServiceImpl(
                mock(BookRepository.class), new BookFactory());
        assertThat(catalogingService.fetchAllBooks(), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void return_details_of_all_books() {
        String firstBookId = "id-1234";
        String secondBookId = "id-5678";

        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.getAll()).thenReturn(List.of(
                new DomainDrivenDesignBook(firstBookId),
                new HeadFirstDesignPatternsBook(secondBookId)));

        List<BookDTO> allFetchedBookDTOS =
                new CatalogingServiceImpl(bookRepository, new BookFactory())
                        .fetchAllBooks();

        assertThat(allFetchedBookDTOS.get(0),
                is(equalTo(new DomainDrivenDesignBookDTO(firstBookId))));

        assertThat(allFetchedBookDTOS.get(1),
                is(equalTo(new HeadFirstDesignPatternsBookDTO(secondBookId))));
    }
}
