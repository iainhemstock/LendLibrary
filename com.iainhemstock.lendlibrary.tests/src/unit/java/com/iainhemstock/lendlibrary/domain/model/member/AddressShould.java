package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public final class AddressShould {

    @Test
    public void throw_when_initialized_with_null_address_line_1() {
        try {
            new Address(null,
                    mock(AddressLine2.class), mock(City.class), mock(County.class), mock(Postcode.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address line 1 is required")));
        }
    }

    @Test
    public void throw_when_initialized_with_null_address_line_2() {
        try {
            new Address(mock(AddressLine1.class),
                    null,
                    mock(City.class), mock(County.class), mock(Postcode.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address line 2 is required")));
        }
    }

    @Test
    public void throw_when_initialized_with_null_city() {
        try {
            new Address(mock(AddressLine1.class), mock(AddressLine2.class),
                    null,
                    mock(County.class), mock(Postcode.class));
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("City is required")));
        }
    }

    @Test
    public void throw_when_initialized_with_null_county() {
        try {
            new Address(mock(AddressLine1.class), mock(AddressLine2.class), mock(City.class),
                    null,
                    mock(Postcode.class));
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("County is required")));
        }
    }

    @Test
    public void throw_when_initialized_with_null_postcode() {
        try {
            new Address(mock(AddressLine1.class), mock(AddressLine2.class), mock(City.class), mock(County.class),
                    null);
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Postcode is required")));
        }
    }
}
