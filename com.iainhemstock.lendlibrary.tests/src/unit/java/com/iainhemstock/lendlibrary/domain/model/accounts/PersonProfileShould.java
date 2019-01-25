package com.iainhemstock.lendlibrary.domain.model.accounts;

import com.iainhemstock.lendlibrary.domain.model.accounts.PersonProfile;
import com.iainhemstock.lendlibrary.domain.model.accounts.Address;
import com.iainhemstock.lendlibrary.domain.model.accounts.ContactDetails;
import com.iainhemstock.lendlibrary.domain.model.accounts.FullName;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class PersonProfileShould {

    @Test
    public void throw_if_builder_receives_null_full_name() {
        try {
            new PersonProfile.Builder().withFullName(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_full_name_to_a_member_profile_using_builder() {
        FullName fullName = Mockito.mock(FullName.class);
        final PersonProfile personProfile = new PersonProfile.Builder().withFullName(fullName).build();
        assertThat(personProfile.getFullName(), is(equalTo(fullName)));
    }

    @Test
    public void throw_if_builder_receives_null_address() {
        try {
            new PersonProfile.Builder().withAddress(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_address_to_a_member_profile_using_builder() {
        final Address address = Mockito.mock(Address.class);
        final PersonProfile personProfile = new PersonProfile.Builder().withAddress(address).build();
        assertThat(personProfile.getAddress(), is(equalTo(address)));
    }

    @Test
    public void throw_if_builder_receives_null_contact_details() {
        try {
            new PersonProfile.Builder().withContactDetails(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_contact_details_to_member_profile_using_builder() {
        final ContactDetails contactDetails = Mockito.mock(ContactDetails.class);
        final PersonProfile personProfile = new PersonProfile.Builder().withContactDetails(contactDetails).build();
        assertThat(personProfile.getContactDetails(), is(equalTo(contactDetails)));
    }
}
