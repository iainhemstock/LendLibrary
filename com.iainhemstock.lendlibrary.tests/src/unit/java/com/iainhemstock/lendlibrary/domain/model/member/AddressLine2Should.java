package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class AddressLine2Should {

    @Test
    public void throw_if_address2_argument_is_null() {
        try {
            new AddressLine2(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address line 2 is required")));
        }
    }

    @Test
    public void return_address2_as_string() {
        final String add2 = "The Town";
        final AddressLine2 addressLine2 = new AddressLine2(add2);
        assertThat(addressLine2.toString(), is(equalTo(add2)));
    }
}
