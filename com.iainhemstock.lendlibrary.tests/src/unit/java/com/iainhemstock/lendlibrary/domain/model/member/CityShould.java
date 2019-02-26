package com.iainhemstock.lendlibrary.domain.model.registration;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class CityShould {

    @Test
    public void throw_if_city_argument_is_null() {
        try {
            new City(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_city_argument_is_empty() {
        try {
            new City("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("city cannot be empty")));
        }
    }

    @Test
    public void return_city_as_string() {
        final String cityString = "The City";
        final City city = new City(cityString);
        assertThat(city.toString(), is(equalTo(cityString)));
    }
}
