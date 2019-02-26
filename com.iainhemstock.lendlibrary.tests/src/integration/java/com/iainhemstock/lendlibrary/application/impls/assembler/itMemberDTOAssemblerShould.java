package com.iainhemstock.lendlibrary.application.impls.assembler;

import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class itMemberDTOAssemblerShould {

    private static final String MEM1_ACCOUNT_ID = "9ba0d1ad-cb44-4ca4-bb0b-a0b9cab13d97";
    private static final String MEM1_FIRST_NAME = "Jane";
    private static final String MEM1_LAST_NAME = "Doe";
    private static final String MEM1_ADDRESS1 = "1 Ross Avenue";
    private static final String MEM1_ADDRESS2 = "Levenshulme";
    private static final String MEM1_CITY = "Manchester";
    private static final String MEM1_COUNTY = "Greater Manchester";
    private static final String MEM1_POSTCODE = "M192HW";
    private static final String MEM1_EMAIL = "janedoe@gmail.com";
    private static final String MEM1_CONTACT_NUMBER = "01619487013";

    private static final String MEM2_ACCOUNT_ID = "e9a08de2-cab0-4a16-ba2e-1fc5c58664e7";
    private static final String MEM2_FIRST_NAME = "John";
    private static final String MEM2_LAST_NAME = "Smith";
    private static final String MEM2_ADDRESS1 = "456 The Avenue";
    private static final String MEM2_ADDRESS2 = "Great Parndon";
    private static final String MEM2_CITY = "Harlow";
    private static final String MEM2_COUNTY = "Essex";
    private static final String MEM2_POSTCODE = "CM194HG";
    private static final String MEM2_CONTACT_NUMBER = "01992967603";
    private static final String MEM2_EMAIL = "jsmith@gmail.com";

    private MemberDTOAssembler assembler;

    @Before
    public void setUp() throws Exception {
        assembler = new MemberDTOAssembler();
    }

    @Test
    public void return_empty_list_when_no_memberships_exist() {
        List<Member> emptyMembershipsList = new ArrayList<>();
        List<MemberDTO> actualDTOs = assembler.toDTOList(emptyMembershipsList);
        List<MemberDTO> expectedDTOs = new ArrayList<>();

        assertThat(actualDTOs, is(equalTo(expectedDTOs)));
    }

    @Test
    public void return_list_of_memberships_converted_to_dtos() {
        List<Member> members = makeMembers();
        List<MemberDTO> expectedDTOs = makeExpectedDTOs();
        List<MemberDTO> actualDTOs = assembler.toDTOList(members);
        assertThat(actualDTOs, is(equalTo(expectedDTOs)));
    }

    @Test
    public void return_membership_converted_to_dto() {
        Member alisonMarlowMember = getAlisonMarlowMember();

        MemberDTO memberDTO = assembler.toDTO(alisonMarlowMember);

        FullName fullName = alisonMarlowMember.getFullName();
        Address address = alisonMarlowMember.getAddress();
        ContactDetails contactDetails = alisonMarlowMember.getContactDetails();

        assertThat(memberDTO.getMemberId(), is(equalTo(alisonMarlowMember.getId().toString())));
        assertThat(memberDTO.getFirstName(), is(equalTo(fullName.getFirstName().toString())));
        assertThat(memberDTO.getLastName(), is(equalTo(fullName.getLastName().toString())));
        assertThat(memberDTO.getAddressLine1(), is(equalTo(address.getAddressLine1().toString())));
        assertThat(memberDTO.getAddressLine2(), is(equalTo(address.getAddressLine2().toString())));
        assertThat(memberDTO.getCity(), is(equalTo(address.getCity().toString())));
        assertThat(memberDTO.getCounty(), is(equalTo(address.getCounty().toString())));
        assertThat(memberDTO.getPostcode(), is(equalTo(address.getPostcode().toString())));
        assertThat(memberDTO.getContactNumber(), is(equalTo(contactDetails.getTelephone().toString())));
        assertThat(memberDTO.getEmail(), is(equalTo(contactDetails.getEmail().toString())));
    }

    private Member getAlisonMarlowMember() {
        return new MemberFactory().create(
                "id-1234",
                "Alison", "Marlow",
                "1 Ross Avenue", "Levenshulme", "Manchester", "Greater Manchester", "M192HW",
                "01619487013", "alisonmarlow@gmail.com");
    }

    private List<MemberDTO> makeExpectedDTOs() {
        return List.of(
                new MemberDTO(
                        MEM1_ACCOUNT_ID,
                        MEM1_FIRST_NAME, MEM1_LAST_NAME,
                        MEM1_ADDRESS1, MEM1_ADDRESS2, MEM1_CITY, MEM1_COUNTY, MEM1_POSTCODE,
                        MEM1_CONTACT_NUMBER, MEM1_EMAIL),
                new MemberDTO(
                        MEM2_ACCOUNT_ID,
                        MEM2_FIRST_NAME, MEM2_LAST_NAME,
                        MEM2_ADDRESS1, MEM2_ADDRESS2, MEM2_CITY, MEM2_COUNTY, MEM2_POSTCODE,
                        MEM2_CONTACT_NUMBER, MEM2_EMAIL));
    }

    private List<Member> makeMembers() {
        return List.of(
                new MemberFactory().create(
                        MEM1_ACCOUNT_ID,
                        MEM1_FIRST_NAME, MEM1_LAST_NAME,
                        MEM1_ADDRESS1, MEM1_ADDRESS2, MEM1_CITY, MEM1_COUNTY, MEM1_POSTCODE,
                        MEM1_CONTACT_NUMBER, MEM1_EMAIL),
                new MemberFactory().create(
                        MEM2_ACCOUNT_ID,
                        MEM2_FIRST_NAME, MEM2_LAST_NAME,
                        MEM2_ADDRESS1, MEM2_ADDRESS2, MEM2_CITY, MEM2_COUNTY, MEM2_POSTCODE,
                        MEM2_CONTACT_NUMBER, MEM2_EMAIL));
    }
}
