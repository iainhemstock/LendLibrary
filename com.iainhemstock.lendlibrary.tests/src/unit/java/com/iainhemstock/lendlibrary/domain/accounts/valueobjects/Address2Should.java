package com.iainhemstock.lendlibrary.domain.accounts.valueobjects;

import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class Address2Should {

    @Test
    public void throw_if_address2_argument_is_null() {
        try {
            new Address2(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void return_address2_as_string() {
        final String add2 = "The Town";
        final Address2 address2 = new Address2(add2);
        assertThat(address2.toString(), is(equalTo(add2)));
    }
}
