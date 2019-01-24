package com.iainhemstock.lendlibrary.domain.books.entities;

import com.iainhemstock.lendlibrary.domain.books.valueobjects.*;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class BookProfileShould {

    @Test
    public void throw_if_builder_receives_null_isbn() {
        try {
            new BookProfile.Builder().withIsbn(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Isbn is required")));
        }
    }

    @Test
    public void add_isbn_to_book_profile_using_builder() {
        Isbn isbn = Mockito.mock(Isbn.class);
        BookProfile bookProfile = new BookProfile.Builder().withIsbn(isbn).build();
        assertThat(bookProfile.getIsbn(), is(equalTo(isbn)));
    }

    @Test
    public void throw_if_builder_receives_null_title() {
        try {
            new BookProfile.Builder().withTitle(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Title is required")));
        }
    }

    @Test
    public void add_title_to_book_profile_using_builder() {
        Title title = Mockito.mock(Title.class);
        BookProfile bookProfile = new BookProfile.Builder().withTitle(title).build();
        assertThat(bookProfile.getTitle(), is(equalTo(title)));
    }

    @Test
    public void throw_if_builder_receives_null_subtitle() {
        try {
            new BookProfile.Builder().withSubtitle(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Subtitle is required")));
        }
    }

    @Test
    public void add_subtitle_to_book_profile_using_builder() {
        Subtitle subtitle = Mockito.mock(Subtitle.class);
        BookProfile bookProfile = new BookProfile.Builder().withSubtitle(subtitle).build();
        assertThat(bookProfile.getSubtitle(), is(equalTo(subtitle)));
    }

    @Test
    public void throw_if_builder_receives_null_author_id() {
        try {
            new BookProfile.Builder().withAuthorId(null);
            fail("method under test should have thrown NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Author Id is required")));
        }
    }

    @Test
    public void add_author_id_to_book_profile_using_builder() {
        AuthorId authorId = Mockito.mock(AuthorId.class);
        BookProfile bookProfile = new BookProfile.Builder().withAuthorId(authorId).build();
        assertThat(bookProfile.getAuthorId(), is(equalTo(authorId)));
    }

    @Test
    public void throw_if_builder_receives_null_publisherId() {
        try {
            new BookProfile.Builder().withPublisherId(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Publisher Id is required")));
        }
    }

    @Test
    public void add_publisher_id_to_book_profile_using_builder() {
        PublisherId publisherId = Mockito.mock(PublisherId.class);
        BookProfile bookProfile = new BookProfile.Builder().withPublisherId(publisherId).build();
        assertThat(bookProfile.getPublisherId(), is(equalTo(publisherId)));
    }

    @Test
    public void throw_if_builder_receives_null_year() {
        try {
            new BookProfile.Builder().withYearPublished(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Year is required")));
        }
    }

    @Test
    public void add_year_published_to_book_profile_using_builder() {
        Year yearPublished = Mockito.mock(Year.class);
        BookProfile bookProfile = new BookProfile.Builder().withYearPublished(yearPublished).build();
        assertThat(bookProfile.getYearPublished(), is(equalTo(yearPublished)));
    }

    @Test
    public void throw_if_builder_receives_null_page_count() {
        try {
            new BookProfile.Builder().withPageCount(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Page count is required")));
        }
    }

    @Test
    public void add_page_count_to_book_profile_using_builder() {
        PageCount pageCount = Mockito.mock(PageCount.class);
        BookProfile bookProfile = new BookProfile.Builder().withPageCount(pageCount).build();
        assertThat(bookProfile.getPageCount(), is(equalTo(pageCount)));
    }

    @Test
    public void throw_if_builder_receives_null_description() {
        try {
            new BookProfile.Builder().withDescription(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Description is required")));
        }
    }

    @Test
    public void add_description_to_book_profile_using_builder() {
        Description description = Mockito.mock(Description.class);
        BookProfile bookProfile = new BookProfile.Builder().withDescription(description).build();
        assertThat(bookProfile.getDescription(), is(equalTo(description)));
    }
}
