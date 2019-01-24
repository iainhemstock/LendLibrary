package com.iainhemstock.lendlibrary.domain.books.entities;

import com.iainhemstock.lendlibrary.domain.books.valueobjects.BookId;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class BookShould {

    @Test
    public void return_its_id() {
        BookId bookId = Mockito.mock(BookId.class);
        BookProfile bookProfile = Mockito.mock(BookProfile.class);
        Book book = new Book(bookId, bookProfile);
        assertThat(book.getBookId(), is(equalTo(bookId)));
    }
}
