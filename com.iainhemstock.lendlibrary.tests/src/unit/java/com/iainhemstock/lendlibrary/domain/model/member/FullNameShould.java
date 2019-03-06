package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class FullNameShould {

    @Test
    public void throw_if_passed_a_null_first_name() {
        try {
            new FullName(null, new LastName("Hart"));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("First name is required")));
        }
    }

    @Test
    public void throw_if_passed_a_null_last_name() {
        try {
            new FullName(new FirstName("Colin"), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Last name is required")));
        }
    }
}
