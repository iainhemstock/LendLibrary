package com.iainhemstock.lendlibrary.domain.model.book;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
public final class DescriptionShould {

    @Test
    @Parameters({
            "",
            "The description of the book"
    })
    public void return_description_as_string(final String descriptionString) {
        Description description = new Description(descriptionString);
        assertThat(description.toString(), is(equalTo(descriptionString)));
    }
}
