package com.iainhemstock.lendlibrary.domain.model.books;

import com.iainhemstock.lendlibrary.domain.model.books.AuthorId;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public final class AuthorIdShould {

    @Test
    @Parameters({ "0", "-1" })
    public void throw_if_id_argument_is_zero_or_less(int invalidId) {
        try {
            new AuthorId(invalidId);
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("id cannot be zero or negative")));
        }
    }

    @Test
    public void return_id_as_string() {
        AuthorId id = new AuthorId(1);
        assertThat(id.toString(), is(equalTo("1")));
    }
}
