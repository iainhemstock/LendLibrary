package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.Telephone;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class TelephoneShould {

    @Test
    public void throw_if_telephone_argument_is_null() {
        try {
            new Telephone(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_telephone_argument_is_empty() {
        try {
            new Telephone("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("telephone cannot be empty")));
        }
    }

    @Test
    public void return_telephone_as_string() {
        final String telephoneString = "01234567890";
        final Telephone telephone = new Telephone(telephoneString);
        assertThat(telephone.toString(), is(equalTo(telephoneString)));
    }
}
