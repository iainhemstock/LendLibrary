package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FirstNameShould {

    @Test
    public void throw_when_name_argument_is_null() {
        try {
            new FirstName(null);
            fail("expected method under test to throw an NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument is null")));
        }
    }

    @Test
    public void throw_when_name_argument_is_empty() {
        try {
            new FirstName("");
            fail("expected method under test to throw an IllegalArgumentException but it didn't");
        }
        catch(IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("first name cannot be empty")));
        }
    }

    @Test
    public void return_first_name_as_a_string() {
        final String name = "Jane";
        final FirstName firstName = new FirstName(name);
        assertThat(firstName.toString(), is(equalTo(name)));
    }
}
