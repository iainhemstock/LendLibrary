package com.iainhemstock.lendlibrary.application.registering;

import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public abstract class RegisteringServiceAdapterShould {

    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock protected MemberFactory memberFactory;
    @Mock protected MemberRepository memberRepository;
    @Mock protected MemberDTOAssembler memberDTOAssembler;

    protected abstract RegisteringServiceAdapter getMembershipServiceAdapter();
    protected abstract RegisteringServiceAdapter getMembershipServiceAdapterWithNullFactory();
    protected abstract RegisteringServiceAdapter getMembershipServiceAdapterWithNullRepository();
    protected abstract RegisteringServiceAdapter getMembershipServiceAdapterWithNullAssembler();

    @Test
    public void throw_if_adapter_is_initialized_with_null_factory() {
        try {
            getMembershipServiceAdapterWithNullFactory();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member factory is required")));
        }
    }

    @Test
    public void throw_if_adapter_is_initialized_with_null_repository() {
        try {
            getMembershipServiceAdapterWithNullRepository();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member repository is required")));
        }
    }

    @Test
    public void throw_if_adapter_is_initialized_with_null_assembler() {
        try {
            getMembershipServiceAdapterWithNullAssembler();
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member DTO assembler is required")));
        }
    }

    @Test
    public void save_membership_in_repo_and_return_membership_id_when_opening_new_membership() {
        MemberId memberId = Mockito.mock(MemberId.class);
        Member member = Mockito.mock(Member.class);

        when(memberRepository.nextId()).thenReturn(memberId);
        when(memberId.toString()).thenReturn("id-1234");
        when(memberFactory.create(
                "id-1234",
                getAlisonMarlowMembershipDTO().getFirstName(), getAlisonMarlowMembershipDTO().getLastName(),
                getAlisonMarlowMembershipDTO().getAddressLine1(), getAlisonMarlowMembershipDTO().getAddressLine2(),
                getAlisonMarlowMembershipDTO().getCity(), getAlisonMarlowMembershipDTO().getCounty(),
                getAlisonMarlowMembershipDTO().getPostcode(),
                getAlisonMarlowMembershipDTO().getContactNumber(), getAlisonMarlowMembershipDTO().getEmail()))
                .thenReturn(member);

        String actualMembershipId = getMembershipServiceAdapter().openMembership(getAlisonMarlowMembershipDTO());
        assertThat(actualMembershipId, is(equalTo("id-1234")));
        verify(memberRepository).add(member);
    }

    @Test
    public void throw_when_trying_to_fetch_membership_with_null_id() {
        try {
            getMembershipServiceAdapter().fetchMembership(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_fetch_membership_that_does_not_exist() {
        doThrow(new MemberNotFoundException("Member with id <id-2345> not found"))
                .when(memberRepository).getById(any(MemberId.class));

        String absentMembershipId = "id-2345";
        try {
            getMembershipServiceAdapter().fetchMembership(absentMembershipId);
            fail("expected method under test to throw MemberNotFoundException but it didn't");
        } catch (MemberNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member with id <id-2345> not found")));
        }
    }

    @Test
    public void return_details_of_given_membership() {
        MemberId memberId = Mockito.mock(MemberId.class);
        Member member = Mockito.mock(Member.class);

        when(memberRepository.nextId()).thenReturn(memberId);
        when(memberRepository.getById(any(MemberId.class))).thenReturn(member);
        when(memberId.toString()).thenReturn("id-1234");
        when(memberDTOAssembler.toDTO(member)).thenReturn(getAlisonMarlowMembershipDTO("id-1234"));

        MemberDTO alisonMarlowMemberDTO = getAlisonMarlowMembershipDTO();
        String membershipIdStr = getMembershipServiceAdapter().openMembership(alisonMarlowMemberDTO);

        MemberDTO memberDTO = getMembershipServiceAdapter().fetchMembership(membershipIdStr);

        assertThat(memberDTO.getMembershipId(), is(equalTo(membershipIdStr)));
        assertThat(memberDTO.getFirstName(), is(equalTo(alisonMarlowMemberDTO.getFirstName())));
        assertThat(memberDTO.getLastName(), is(equalTo(alisonMarlowMemberDTO.getLastName())));
        assertThat(memberDTO.getAddressLine1(), is(equalTo(alisonMarlowMemberDTO.getAddressLine1())));
        assertThat(memberDTO.getAddressLine2(), is(equalTo(alisonMarlowMemberDTO.getAddressLine2())));
        assertThat(memberDTO.getCity(), is(equalTo(alisonMarlowMemberDTO.getCity())));
        assertThat(memberDTO.getCounty(), is(equalTo(alisonMarlowMemberDTO.getCounty())));
        assertThat(memberDTO.getPostcode(), is(equalTo(alisonMarlowMemberDTO.getPostcode())));
        assertThat(memberDTO.getContactNumber(), is(equalTo(alisonMarlowMemberDTO.getContactNumber())));
        assertThat(memberDTO.getEmail(), is(equalTo(alisonMarlowMemberDTO.getEmail())));
    }

    @Test
    public void return_empty_list_when_trying_to_fetch_memberships_that_dont_exist() {
        assertThat(getMembershipServiceAdapter().fetchAllMemberships(), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void return_details_of_all_memberships() {
        MemberDTO alisonMarlowMemberDTO = getAlisonMarlowMembershipDTO("id-1234");
        MemberDTO colinHartMemberDTO = getColinHartExistingAccountDTO("id-5678");

        List<Member> membershipsList = List.of(Mockito.mock(Member.class), Mockito.mock(Member.class));
        when(memberRepository.getAll()).thenReturn(membershipsList);
        when(memberDTOAssembler.toDTOList(membershipsList))
                .thenReturn(List.of(alisonMarlowMemberDTO, colinHartMemberDTO));

        List<MemberDTO> allFetchedMemberDTOS = getMembershipServiceAdapter().fetchAllMemberships();

        verify(memberRepository).getAll();
        verify(memberDTOAssembler).toDTOList(membershipsList);
        assertThat(allFetchedMemberDTOS.get(0), is(equalTo(alisonMarlowMemberDTO)));
        assertThat(allFetchedMemberDTOS.get(1), is(equalTo(colinHartMemberDTO)));
    }

    private MemberDTO getAlisonMarlowMembershipDTO() {
        return new MemberDTO(null,
                "Alison", "Marlow",
                "1 Ross Avenue", "Levenshulme", "Manchester", "Greater Manchester", "M192HW",
                "01619487013", "alisonmarlow@gmail.com");
    }

    private MemberDTO getAlisonMarlowMembershipDTO(final String membershipId) {
        return new MemberDTO(
                membershipId,
                getAlisonMarlowMembershipDTO().getFirstName(), getAlisonMarlowMembershipDTO().getLastName(),
                getAlisonMarlowMembershipDTO().getAddressLine1(), getAlisonMarlowMembershipDTO().getAddressLine2(),
                getAlisonMarlowMembershipDTO().getCity(), getAlisonMarlowMembershipDTO().getCounty(),
                getAlisonMarlowMembershipDTO().getPostcode(),
                getAlisonMarlowMembershipDTO().getContactNumber(), getAlisonMarlowMembershipDTO().getEmail());
    }

    private MemberDTO getColinHartExistingAccountDTO(String membershipId) {
        return new MemberDTO(
                membershipId,
                "John", "Smith",
                "456 The Avenue", "Great Parndon", "Harlow", "Essex", "CM194HG",
                "01992967603", "jsmith@gmail.com");
    }
}
