package com.iainhemstock.lendlibrary.applicationservices.books;

import com.iainhemstock.lendlibrary.domain.model.books.*;
import com.iainhemstock.lendlibrary.domain.model.books.BookRepository;
import com.iainhemstock.lendlibrary.applicationservices.CatalogueService;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewBookToCatalogue {

    private CatalogueService catalogueService;
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        final URL resource = getClass().getResource("/beans.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext(resource.toString());
        catalogueService = context.getBean("SimpleCatalogueService", CatalogueService.class);
        bookRepository = context.getBean("BookInMemoryRepository", BookRepository.class);
    }

    @Test
    public void add_new_book_to_catalogue() {
        Book book = make_test_book();
        catalogueService.addBook(book);
        assertThatBookRepositorySizeIs(1);
        assertThatBookRepositoryContains(book);
    }

    private Book make_test_book() {
        BookId bookId = bookRepository.nextId();
        BookProfile bookProfile = new BookProfile.Builder()
                .withIsbn(new Isbn("1234567890"))
                .withTitle(new Title("Clean Architecture"))
                .withSubtitle(new Subtitle("A Craftman's Guide to Software Structure and Design"))
                .withAuthorId(new AuthorId(1))
                .withPublisherId(new PublisherId(1))
                .withYearPublished(new Year(2012))
                .withPageCount(new PageCount(404))
                .withDescription(new Description("The description of the book"))
            .build();
        return new Book(bookId, bookProfile);
    }

    private void assertThatBookRepositorySizeIs(final int size) {
        assertThat(bookRepository.size(), is(IsEqual.equalTo(size)));
    }

    private void assertThatBookRepositoryContains(final Book book) {
        assertThat(bookRepository.contains(book), is(IsEqual.equalTo(true)));
    }
}
