package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class PostcodeShould {

    @Test
    public void throw_if_postcode_argument_is_null() {
        try {
            new Postcode(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_postcode_argument_is_empty() {
        try {
            new Postcode("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("postcode cannot be empty")));
        }
    }

    @Test
    public void return_postcode_as_string() {
        final String postcodeString = "AB12C34";
        final Postcode postcode = new Postcode(postcodeString);
        assertThat(postcode.toString(), is(equalTo(postcodeString)));
    }
}
