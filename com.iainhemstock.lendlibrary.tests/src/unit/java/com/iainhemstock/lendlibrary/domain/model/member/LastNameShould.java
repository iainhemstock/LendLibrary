package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LastNameShould {

    @Test
    public void throw_if_name_argument_is_null() {
        try {
            new LastName(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_name_argument_is_empty() {
        try {
            new LastName("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("last name cannot be empty")));
        }
    }

    @Test
    public void return_last_name_as_string() {
        final String name = "Doe";
        final LastName lastName = new LastName(name);
        assertThat(lastName.toString(), is(equalTo(name)));
    }
}
