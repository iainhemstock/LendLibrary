package com.iainhemstock.lendlibrary.domain.shared;

import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EntityIdShould {

    private String stringId = UUID.randomUUID().toString();

    public EntityId getId(final String stringId) {
        return new EntityId(stringId);
    }

    @Test
    public void throw_if_initialized_with_null_string() {
        try {
            getId(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Id is required")));
        }
    }

    @Test
    public void throw_if_initialized_with_empty_string() {
        try {
            getId("");
            fail("expected method under test to throw IllegalArgumentException but it didn't");
        }
        catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is(equalTo("Id cannot be empty")));
        }
    }

    @Test
    public void returns_id_as_string() {
        assertThat(getId(stringId).toString(), is(equalTo(stringId)));
    }

}
