package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.ContactDetails;
import com.iainhemstock.lendlibrary.domain.model.accounts.Email;
import com.iainhemstock.lendlibrary.domain.model.accounts.Telephone;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ContactDetailsShould {

    @Test
    public void throw_if_builder_receives_null_telephone() {
        try {
            new ContactDetails.Builder().withTelephone(null);
            fail("expected method under test to throw NullpointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_telephone_contact_details_using_builder() {
        final Telephone telephone = Mockito.mock(Telephone.class);
        final ContactDetails contactDetails = new ContactDetails.Builder().withTelephone(telephone).build();
        assertThat(contactDetails.getTelephone(), is(equalTo(telephone)));
    }

    @Test
    public void throw_if_builder_receives_null_email() {
        try {
            new ContactDetails.Builder().withEmail(null);
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_email_to_contact_details_using_builder() {
        final Email email = Mockito.mock(Email.class);
        final ContactDetails contactDetails = new ContactDetails.Builder().withEmail(email).build();
        assertThat(contactDetails.getEmail(), is(equalTo(email)));
    }
}
