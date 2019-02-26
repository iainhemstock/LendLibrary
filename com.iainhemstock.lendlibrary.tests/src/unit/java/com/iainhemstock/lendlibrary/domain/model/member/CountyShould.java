package com.iainhemstock.lendlibrary.domain.model.registration;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CountyShould {

    @Test
    public void throw_if_county_argument_is_null() {
        try {
            new County(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_county_argument_is_empty() {
        try {
            new County("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("county cannot be empty")));
        }
    }

    @Test
    public void return_county_as_string() {
        final String countyString = "The County";
        final County county = new County(countyString);
        assertThat(county.toString(), is(equalTo(countyString)));
    }
}
