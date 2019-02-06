package com.iainhemstock.lendlibrary.domain.model.catalogue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

@RunWith(JUnitParamsRunner.class)
public final class SubtitleShould {

    @Test
    public void throw_if_subtitle_argument_is_null() {
        try {
            new Subtitle(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    @Parameters({
            "",
            "A Craftman's Guide to Software Structure and Design"
    })
    public void return_subtitle_as_string(final String subtitleString) {
        Subtitle subtitle = new Subtitle(subtitleString);
        assertThat(subtitle.toString(), is(equalTo(subtitleString)));
    }
}
