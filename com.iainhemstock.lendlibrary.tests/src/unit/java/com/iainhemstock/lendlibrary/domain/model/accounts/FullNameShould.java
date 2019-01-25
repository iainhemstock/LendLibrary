package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.FirstName;
import com.iainhemstock.lendlibrary.domain.model.accounts.FullName;
import com.iainhemstock.lendlibrary.domain.model.accounts.LastName;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FullNameShould {

    @Test
    public void build_a_full_name_using_builder() {
        FirstName firstName = Mockito.mock(FirstName.class);
        LastName lastName = Mockito.mock(LastName.class);
        FullName fullName = new FullName.Builder().withFirstName(firstName).withLastName(lastName).build();
        assertThat(fullName.getFirstName(), is(equalTo(firstName)));
        assertThat(fullName.getLastName(), is(equalTo(lastName)));
    }

    @Test
    public void throw_if_passed_a_null_first_name() {
        try {
            new FullName.Builder().withFirstName(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void throw_if_passed_a_null_last_name() {
        try {
            new FullName.Builder().withLastName(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }
}
