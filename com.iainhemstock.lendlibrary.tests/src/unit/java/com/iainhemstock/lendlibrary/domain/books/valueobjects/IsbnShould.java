package com.iainhemstock.lendlibrary.domain.books.valueobjects;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public final class IsbnShould {

    @Test
    public void return_isbn_as_string() {
        final String isbnString = "1234567890";
        final Isbn isbn = new Isbn(isbnString);
        assertThat(isbn.toString(), is(equalTo(isbnString)));
    }

    @Test
    public void throw_if_isbn_argument_is_null() {
        try {
            new Isbn(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    @Parameters({
            "-234567890",
            "1-34567890",
            "12-4567890",
            "123-567890",
            "1234-67890",
            "12345-7890",
            "123456-890",
            "1234567-90",
            "12345678-0",
            "123456789-",
            "12345678901234",
            "123456789012",
            "12345678901",
            "123456789",
            "12345678",
            "1234567",
            "123456",
            "12345",
            "1234",
            "123",
            "12",
            "1",
            "",
    })
    public void throw_if_isbn_is_not_correct_length_or_does_not_exclusively_contain_digits(String invalidIsbn) {
        try {
            new Isbn(invalidIsbn);
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("isbn must be 10 or 13 digits only")));
        }
    }
}
