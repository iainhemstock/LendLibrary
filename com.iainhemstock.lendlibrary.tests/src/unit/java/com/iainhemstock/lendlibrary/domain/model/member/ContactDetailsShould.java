package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public final class ContactDetailsShould {

    @Test
    public void throw_when_initializing_with_null_telephone() {
        try {
            new ContactDetails(null, mock(Email.class));
            fail("expected method under test to throw NullpointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Telephone is required")));
        }
    }

    @Test
    public void throw_when_initializing_with_null_email() {
        try {
            new ContactDetails(mock(Telephone.class), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Email is required")));
        }
    }
}
