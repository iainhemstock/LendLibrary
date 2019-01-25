package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.Address1;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class Address1Should {

    @Test
    public void throw_if_address_argument_is_null() {
        try {
            new Address1(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_address_argument_is_empty() {
        try {
            new Address1("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("address1 cannot be empty")));
        }
    }

    @Test
    public void return_address1_as_string() {
        final String add1 = "123 The Street";
        final Address1 address1 = new Address1(add1);
        assertThat(address1.toString(), is(equalTo(add1)));
    }
}
