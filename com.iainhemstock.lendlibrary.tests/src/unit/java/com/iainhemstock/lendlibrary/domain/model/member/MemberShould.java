package com.iainhemstock.lendlibrary.domain.model.member;

import com.iainhemstock.lendlibrary.application.domain.model.member.ColinHartMember;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartRelocatedMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public final class MemberShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock private MemberId memberId;

    @Test
    public void return_its_id() {
        MemberId memberId = new MemberId("id-1234");
        Member member = new Member(memberId, mock(FullName.class), mock(Address.class), mock(ContactDetails.class));
        assertThat(member.getId(), is(equalTo(memberId)));
    }

    @Test
    public void throw_if_membership_is_created_with_null_member_id() {
        try {
            new Member(null, mock(FullName.class), mock(Address.class), mock(ContactDetails.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void throw_if_membership_is_created_with_null_full_name() {
        try {
            new Member(new MemberId("id-1234"), null, mock(Address.class), mock(ContactDetails.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Full name is required")));
        }
    }

    @Test
    public void throw_if_membership_is_created_with_null_address() {
        try {
            new Member(new MemberId("id-1234"), mock(FullName.class), null, mock(ContactDetails.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Address is required")));
        }
    }

    @Test
    public void throw_if_builder_receives_null_contact_details() {
        try {
            new Member(new MemberId("id-1234"), mock(FullName.class), mock(Address.class), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Contact details are required")));
        }
    }

    @Test
    public void rename_itself() {
        Member member = new ColinHartMember("id-1234");
        FullName updatedName = new FullName(new FirstName("George"), new LastName("Jones"));
        member.renameTo(updatedName);

        assertThat(member.getFullName(), is(equalTo(updatedName)));
    }

    @Test
    public void relocate_to_new_address() {
        MemberId memberId = new MemberId("id-1234");
        MemberDTO relocatedMemberDTO = new ColinHartRelocatedMemberDTO(memberId.toString());
        Member member = new ColinHartMember(memberId.toString());

        Address relocatedAddress = new Address(
                new AddressLine1(relocatedMemberDTO.getAddressLine1()),
                new AddressLine2(relocatedMemberDTO.getAddressLine2()),
                new City(relocatedMemberDTO.getCity()),
                new County(relocatedMemberDTO.getCounty()),
                new Postcode(relocatedMemberDTO.getPostcode()));

        member.relocateTo(relocatedAddress);

        assertThat(member.getAddress(), is(equalTo(relocatedAddress)));
    }

    @Test
    public void update_contact_details() {
        ContactDetails updatedContactDetails = new ContactDetails(
                new Telephone("01924363713"),
                new Email("colin-hart@btinternet.com"));

        Member member = new ColinHartMember("id-1234");
        member.updateContactDetailsTo(updatedContactDetails);

        assertThat(member.getContactDetails(),
                is(equalTo(updatedContactDetails)));
    }
}
