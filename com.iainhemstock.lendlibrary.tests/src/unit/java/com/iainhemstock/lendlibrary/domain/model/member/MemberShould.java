package com.iainhemstock.lendlibrary.domain.model.member;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class MembershipShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock private MembershipId membershipId;

    @Test
    public void throw_if_membership_is_created_with_null_membership_d() {
        try {
            new Membership.Builder(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Membership id is required")));
        }
    }

    @Test
    public void return_its_id() {
        Membership membership = getMembershipBuilder().build();
        assertThat(membership.getId(), is(equalTo(membershipId)));
    }

    @Test
    public void throw_if_builder_receives_null_full_name() {
        try {
            getMembershipBuilder().withFullName(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_full_name_to_a_membership_using_builder() {
        FullName fullName = Mockito.mock(FullName.class);
        final Membership membership = getMembershipBuilder().withFullName(fullName).build();
        assertThat(membership.getFullName(), is(equalTo(fullName)));
    }

    @Test
    public void throw_if_builder_receives_null_address() {
        try {
            getMembershipBuilder().withAddress(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_address_to_a_membership_using_builder() {
        final Address address = Mockito.mock(Address.class);
        final Membership membership = getMembershipBuilder().withAddress(address).build();
        assertThat(membership.getAddress(), is(equalTo(address)));
    }

    @Test
    public void throw_if_builder_receives_null_contact_details() {
        try {
            getMembershipBuilder().withContactDetails(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_contact_details_to_membership_using_builder() {
        final ContactDetails contactDetails = Mockito.mock(ContactDetails.class);
        final Membership membership = getMembershipBuilder().withContactDetails(contactDetails).build();
        assertThat(membership.getContactDetails(), is(equalTo(contactDetails)));
    }

    private Membership.Builder getMembershipBuilder() {
        return new Membership.Builder(membershipId);
    }
}
