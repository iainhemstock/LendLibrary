package com.iainhemstock.lendlibrary.domain.model.registration;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public final class EmailShould {

    @Test
    public void throw_if_email_argument_is_null() {
        try {
            new Email(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    @Parameters({
            "@gmail.com",
            "janedoe@.com",
            "janedoe@gmail",
            "janedoegmail.com"
    })
    public void throw_if_email_argument_is_invalid(String invalidEmailString) {
        try {
            new Email(invalidEmailString);
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("email was ill-formed")));
        }
    }

    @Test
    @Parameters({
            "janedoe@gmail.com",
            "jackjones@btinternet.net",
            "joesmith@isp.net"
    })
    public void return_email_as_string(String validEmailString) {
        final Email email = new Email(validEmailString);
        assertThat(email.toString(), is(equalTo(validEmailString)));
    }
}
