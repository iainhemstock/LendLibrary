package com.iainhemstock.lendlibrary.domain.model.book;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class TitleShould {

    @Test
    public void throw_if_title_argument_is_null() {
        try {
            new Title(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_title_argument_is_empty() {
        try {
            new Title("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("title cannot be empty")));
        }
    }

    @Test
    public void return_title_as_string() {
        String titleString = "Clean Architecture";
        Title title = new Title(titleString);
        assertThat(title.toString(), is(equalTo(titleString)));
    }
}
