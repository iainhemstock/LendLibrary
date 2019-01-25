package com.iainhemstock.lendlibrary.applicationservices.books;

import com.iainhemstock.lendlibrary.domain.model.books.*;
import com.iainhemstock.lendlibrary.domain.model.books.BookRepository;
import com.iainhemstock.lendlibrary.applicationservices.CatalogueService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class GetBooks {

    private CatalogueService catalogueService;
    private BookRepository bookRepository;
    private List<Book> books;

    @Before
    public void setUp() {
        final URL resource = getClass().getResource("/beans.xml");
        final ApplicationContext context = new ClassPathXmlApplicationContext(resource.toString());
        bookRepository = context.getBean("BookInMemoryRepository", BookRepository.class);
        catalogueService = context.getBean("SimpleCatalogueService", CatalogueService.class);
        books = List.of(makeFirstBook(), makeSecondBook(), makeThirdBook());
        catalogueService.addBook(books.get(0));
        catalogueService.addBook(books.get(1));
        catalogueService.addBook(books.get(2));
    }

    @Test
    public void return_all_books_in_catalogue() {
        assertThat(catalogueService.getAllBooks(), is(equalTo(books)));
    }

    @Test
    public void get_book_by_id() {
        Book book = books.get(2);
        assertThat(catalogueService.getBook(book.getBookId()), is(equalTo(book)));
    }

    private Book makeFirstBook() {
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

    private Book makeSecondBook() {
        BookId bookId = bookRepository.nextId();
        BookProfile bookProfile = new BookProfile.Builder()
                .withIsbn(new Isbn("9780571303380"))
                .withTitle(new Title("Memoirs of Count Arthur Strong"))
                .withSubtitle(new Subtitle("Through it all I've always laughed"))
                .withAuthorId(new AuthorId(2))
                .withPublisherId(new PublisherId(2))
                .withYearPublished(new Year(2015))
                .withPageCount(new PageCount(336))
                .withDescription(new Description("The description of Count Arthur's book"))
                .build();
        return new Book(bookId, bookProfile);
    }

    private Book makeThirdBook() {
        BookId bookId = bookRepository.nextId();
        BookProfile bookProfile = new BookProfile.Builder()
                .withIsbn(new Isbn("9780340793114"))
                .withTitle(new Title("Manhattan Is My Beat"))
                .withSubtitle(new Subtitle(""))
                .withAuthorId(new AuthorId(3))
                .withPublisherId(new PublisherId(3))
                .withYearPublished(new Year(2000))
                .withPageCount(new PageCount(294))
                .withDescription(new Description("The description of Manhattan is my beat"))
                .build();
        return new Book(bookId, bookProfile);
    }
}
