package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class AddressLine1Should {

    @Test
    public void throw_if_address_argument_is_null() {
        try {
            new AddressLine1(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address line 1 is required")));
        }
    }

    @Test
    public void throw_if_address_argument_is_empty() {
        try {
            new AddressLine1("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address line 1 cannot be blank")));
        }
    }

    @Test
    public void return_address1_as_string() {
        final String add1 = "123 The Street";
        final AddressLine1 addressLine1 = new AddressLine1(add1);
        assertThat(addressLine1.toString(), is(equalTo(add1)));
    }
}
