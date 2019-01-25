package com.iainhemstock.lendlibrary.domain.shared;

import com.iainhemstock.lendlibrary.domain.shared.BaseId;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class BaseIdShould {

    @Test
    public void throw_if_initialized_with_null_string() {
        try {
            new BaseId(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_initialized_with_empty_string() {
        try {
            new BaseId("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("id cannot be empty")));
        }
    }

    @Test
    public void returns_id_as_string() {
        final String idString = UUID.randomUUID().toString();
        final BaseId id = new BaseId(idString);
        assertThat(id.toString(), is(equalTo(idString)));
    }

}
