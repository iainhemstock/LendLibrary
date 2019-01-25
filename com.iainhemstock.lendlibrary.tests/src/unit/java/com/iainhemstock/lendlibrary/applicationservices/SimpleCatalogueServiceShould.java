package com.iainhemstock.lendlibrary.applicationservices;

import com.iainhemstock.lendlibrary.domain.model.books.Book;
import com.iainhemstock.lendlibrary.domain.model.books.BookId;
import com.iainhemstock.lendlibrary.domain.model.books.BookRepository;
import com.iainhemstock.lendlibrary.domain.shared.RepositoryException;
import org.junit.Before;
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

public final class SimpleCatalogueServiceShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    private CatalogueService catalogueService;
    @Mock private BookRepository bookRepository;

    @Before
    public void setUp() {
        catalogueService = new SimpleCatalogueService(bookRepository);
    }

    @Test
    public void throw_if_books_service_is_initialized_with_null_book_repository() {
        try {
            new SimpleCatalogueService(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book Repository is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_save_null_book_profile() {
        try {
            catalogueService.addBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book Profile is required")));
        }
    }

    @Test
    public void delegate_to_repo_to_save_book_profile() {
        Book book = Mockito.mock(Book.class);
        catalogueService.addBook(book);
        verify(bookRepository).add(book);
    }

    @Test
    public void rethrow_same_exception_if_repository_throws() {
        RepositoryException mockRepositoryException = Mockito.mock(RepositoryException.class);
        Book book = Mockito.mock(Book.class);
        Mockito.doThrow(mockRepositoryException).when(bookRepository).add(book);

        try {
            catalogueService.addBook(book);
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
        assertThat(catalogueService.getAllBooks(), is(equalTo(List.of(firstBook, secondBook, thirdBook))));
    }

    @Test
    public void throw_when_trying_to_find_book_with_null_book_id() {
        try {
            catalogueService.getBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book Id is required")));
        }
    }

    @Test
    public void return_book_profile_found_by_id() {
        Book book = Mockito.mock(Book.class);
        BookId id = Mockito.mock(BookId.class);
        when(bookRepository.getById(id)).thenReturn(book);
        assertThat(catalogueService.getBook(id), is(equalTo(book)));
    }

    @Test
    public void rethrow_repo_exception_when_book_cannot_be_found() {
        BookId nonExistentBookId = Mockito.mock(BookId.class);
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        RepositoryException repositoryException = Mockito.mock(RepositoryException.class);
        CatalogueService catalogueService = new SimpleCatalogueService(bookRepository);

        when(repositoryException.getMessage()).thenReturn("Book does not exist");
        Mockito.doThrow(repositoryException).when(bookRepository).getById(nonExistentBookId);

        try {
            catalogueService.getBook(nonExistentBookId);
            fail("expected method under test to throw RepositoryException but it didn't");
        }
        catch (RepositoryException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book does not exist")));
        }
    }
}
