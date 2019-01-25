package com.iainhemstock.lendlibrary.domain.model.books;

import com.iainhemstock.lendlibrary.domain.model.books.PageCount;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public final class PageCountShould {

    @Test
    @Parameters({ "0", "-1" })
    public void throw_if_page_count_is_less_than_1(final int invalidPageCount) {
        try {
            new PageCount(invalidPageCount);
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("page count cannot be less than 1")));
        }
    }

    @Test
    public void return_page_count_as_string() {
        PageCount pageCount = new PageCount(123);
        assertThat(pageCount.toString(), is(equalTo("123")));
    }
}
