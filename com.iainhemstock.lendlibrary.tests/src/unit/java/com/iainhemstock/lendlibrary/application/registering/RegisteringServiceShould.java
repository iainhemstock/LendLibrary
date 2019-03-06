package com.iainhemstock.lendlibrary.application.registering;

import com.iainhemstock.lendlibrary.application.domain.model.member.AlisonMarlowMember;
import com.iainhemstock.lendlibrary.application.domain.model.member.ColinHartMember;
import com.iainhemstock.lendlibrary.application.registering.dto.AlisonMarlowMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartRelocatedMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.RegisteringServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.MemberRepositoryMemory;
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

public final class RegisteringServiceShould {

    @Test
    public void throw_if_service_is_initialized_with_null_repository() {
        try {
            new RegisteringServiceImpl(null, new MemberFactory(), new MemberDTOAssembler());
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member repository is required")));
        }
    }

    @Test
    public void throw_if_service_is_initialized_with_null_factory() {
        try {
            new RegisteringServiceImpl(mock(MemberRepository.class), null, new MemberDTOAssembler());
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member factory is required")));
        }
    }

    @Test
    public void throw_if_service_is_initialized_with_null_assembler() {
        try {
            new RegisteringServiceImpl(mock(MemberRepository.class), new MemberFactory(), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member DTO assembler is required")));
        }
    }

    @Test
    public void save_member_in_repo_and_return_member_id_when_registering_new_member() {
        MemberId memberId = new MemberId("id-1234");

        MemberRepository memberRepository = mock(MemberRepository.class);
        when(memberRepository.nextId())
                .thenReturn(memberId);

        RegisteringService registeringService = new RegisteringServiceImpl(
                memberRepository, new MemberFactory(), new MemberDTOAssembler());

        String actualMembershipId = registeringService.registerNewMember(
                new ColinHartMemberDTO(memberId.toString()));

        assertThat(actualMembershipId,
                is(equalTo(memberId.toString())));

        verify(memberRepository)
                .add(new ColinHartMember(memberId.toString()));
    }

    @Test
    public void throw_when_trying_to_fetch_member_with_null_id() {
        try {
            new RegisteringServiceImpl(mock(MemberRepository.class), new MemberFactory(), new MemberDTOAssembler())
                    .fetchMember(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_fetch_member_that_does_not_exist() {
        MemberId absentMemberId = new MemberId("id-2345");
        String exceptionMsg = "Member with id <" + absentMemberId + "> not found";

        MemberRepository memberRepository = mock(MemberRepository.class);
        doThrow(new MemberNotFoundException(exceptionMsg))
                .when(memberRepository).getById(new MemberId(absentMemberId.toString()));

        try {
            new RegisteringServiceImpl(memberRepository, new MemberFactory(), new MemberDTOAssembler())
                    .fetchMember(absentMemberId.toString());
            fail("expected method under test to throw MemberNotFoundException but it didn't");
        } catch (MemberNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(exceptionMsg)));
        }
    }

    @Test
    public void return_details_of_given_member() {
        MemberId memberId = new MemberId("id-1234");

        MemberRepository memberRepository = mock(MemberRepository.class);
        when(memberRepository.getById(memberId))
                .thenReturn(new ColinHartMember(memberId.toString()));

        MemberDTO fetchedMemberDTO =
                new RegisteringServiceImpl(memberRepository, new MemberFactory(), new MemberDTOAssembler())
                        .fetchMember(memberId.toString());

        assertThat(fetchedMemberDTO,
                is(equalTo(new ColinHartMemberDTO(memberId.toString()))));
    }

    @Test
    public void return_empty_list_when_trying_to_fetch_members_that_dont_exist() {
        RegisteringServiceImpl registeringService =
                new RegisteringServiceImpl(mock(MemberRepository.class), new MemberFactory(), new MemberDTOAssembler());
        assertThat(registeringService.fetchAllMembers(),
                is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void return_details_of_all_members() {
        String firstMemberId = "id-1234";
        String secondMemberId = "id-5678";

        MemberRepository memberRepository = mock(MemberRepository.class);
        when(memberRepository.getAll())
                .thenReturn(List.of(
                        new AlisonMarlowMember(firstMemberId),
                        new ColinHartMember(secondMemberId)));

        List<MemberDTO> allFetchedMemberDTOS =
                new RegisteringServiceImpl(memberRepository, new MemberFactory(), new MemberDTOAssembler())
                        .fetchAllMembers();

        assertThat(allFetchedMemberDTOS.get(0),
                is(equalTo(new AlisonMarlowMemberDTO(firstMemberId))));

        assertThat(allFetchedMemberDTOS.get(1),
                is(equalTo(new ColinHartMemberDTO(secondMemberId))));
    }

    @Test
    public void throw_when_attempting_to_update_member_that_does_not_exist() {
        MemberId absentMemberId = new MemberId ("id-5678");
        String exceptionMsg = "Member with id <" + absentMemberId.toString() + "> not found";

        MemberRepository memberRepository = mock(MemberRepository.class);
        doThrow(new MemberNotFoundException(exceptionMsg))
                .when(memberRepository).getById(absentMemberId);

        try {
            new RegisteringServiceImpl(memberRepository, new MemberFactory(), new MemberDTOAssembler())
                    .updateExistingMember(new AlisonMarlowMemberDTO(absentMemberId.toString()));
            fail("expected method under test to throw MemberNotFoundException but it didn't");
        } catch (MemberNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo(exceptionMsg)));
        }
    }

    @Test
    public void throw_when_attempting_to_update_member_with_null_details() {
        try {
            new RegisteringServiceImpl(mock(MemberRepository.class), new MemberFactory(), new MemberDTOAssembler())
                    .updateExistingMember(null);
            fail("expected member under test to throw but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member DTO is required")));
        }
    }

    @Test
    public void update_details_of_existing_member() {
        MemberId existingMemberId = new MemberId ("id-1234");
        Member preUpdatedMember = new ColinHartMember(existingMemberId.toString());

        MemberRepository memberRepository = mock(MemberRepository.class);
        when(memberRepository.getById(existingMemberId))
                .thenReturn(preUpdatedMember);

        RegisteringServiceImpl registeringService =
                new RegisteringServiceImpl(memberRepository, new MemberFactory(), new MemberDTOAssembler());

        registeringService.updateExistingMember(new AlisonMarlowMemberDTO(existingMemberId.toString()));
        MemberDTO postUpdatedMemberDTO = registeringService.fetchMember(existingMemberId.toString());

        assertThat(postUpdatedMemberDTO,
                is(equalTo(new AlisonMarlowMemberDTO(existingMemberId.toString()))));
    }
}
