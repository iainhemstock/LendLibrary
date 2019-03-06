package com.iainhemstock.lendlibrary.domain.model.book;

import com.iainhemstock.lendlibrary.application.domain.model.book.HeadFirstDesignPatternsBook;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class BookShould {

    @Test
    public void return_its_id() {
        BookId bookId = new BookId("id-1234");
        Book book = new HeadFirstDesignPatternsBook(bookId.toString());
        assertThat(book.getId(), is(equalTo(bookId)));
    }
}
