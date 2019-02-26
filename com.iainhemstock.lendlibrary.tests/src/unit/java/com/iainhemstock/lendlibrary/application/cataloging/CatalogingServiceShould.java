package com.iainhemstock.lendlibrary.application;

import com.iainhemstock.lendlibrary.application.cataloging.CatalogingService;
import com.iainhemstock.lendlibrary.domain.model.book.Book;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.book.BookRepository;
import com.iainhemstock.lendlibrary.domain.shared.RepositoryException;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class CatalogingServiceShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock private BookRepository bookRepository;

    protected abstract CatalogingService getCatalogueService(final BookRepository bookRepository);

    @Test
    public void throw_if_books_service_is_initialized_with_null_book_repository() {
        try {
            getCatalogueService(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Catalogue repository is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_save_null_book_profile() {
        try {
            getCatalogueService(bookRepository).addBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book profile is required")));
        }
    }

    @Test
    public void delegate_to_repo_to_save_book_profile() {
        Book book = Mockito.mock(Book.class);
        getCatalogueService(bookRepository).addBook(book);
        verify(bookRepository).add(book);
    }

    @Test
    public void rethrow_same_exception_if_repository_throws() {
        RepositoryException mockRepositoryException = Mockito.mock(RepositoryException.class);
        Book book = Mockito.mock(Book.class);
        Mockito.doThrow(mockRepositoryException).when(bookRepository).add(book);

        try {
            getCatalogueService(bookRepository).addBook(book);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException caughtException) {
            assertThat(caughtException, is(equalTo(mockRepositoryException)));
        }
    }

    @Test
    public void return_all_book_profiles_in_repo() {
        final Book firstBook = Mockito.mock(Book.class);
        final Book secondBook = Mockito.mock(Book.class);
        final Book thirdBook = Mockito.mock(Book.class);
        when(bookRepository.getAll()).thenReturn(List.of(firstBook, secondBook, thirdBook));
        assertThat(
                getCatalogueService(bookRepository).getAllBooks(),
                is(equalTo(List.of(firstBook, secondBook, thirdBook))));
    }

    @Test
    public void throw_when_trying_to_find_book_with_null_book_id() {
        try {
            getCatalogueService(bookRepository).getBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book id is required")));
        }
    }

    @Test
    public void return_book_profile_found_by_id() {
        Book book = Mockito.mock(Book.class);
        BookId id = Mockito.mock(BookId.class);
        when(bookRepository.getById(id)).thenReturn(book);
        assertThat(getCatalogueService(bookRepository).getBook(id), is(equalTo(book)));
    }

    @Test
    public void rethrow_repo_exception_when_book_cannot_be_found() {
        BookId nonExistentBookId = Mockito.mock(BookId.class);
        RepositoryException repositoryException = Mockito.mock(RepositoryException.class);

        when(repositoryException.getMessage()).thenReturn("Book does not exist");
        Mockito.doThrow(repositoryException).when(bookRepository).getById(nonExistentBookId);

        try {
            getCatalogueService(bookRepository).getBook(nonExistentBookId);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book does not exist")));
        }
    }
}
