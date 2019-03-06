package com.iainhemstock.lendlibrary.domain.model.book;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class YearShould {

    @Test
    public void return_year_as_string() {
        Year year = new Year(2012);
        assertThat(year.toString(), is(equalTo("2012")));
    }
}
