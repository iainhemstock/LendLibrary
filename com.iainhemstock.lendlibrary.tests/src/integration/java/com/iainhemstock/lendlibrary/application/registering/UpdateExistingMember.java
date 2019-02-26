package com.iainhemstock.lendlibrary.application.registering;

import com.iainhemstock.lendlibrary.application.registering.dto.AlisonMarlowMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartRelocatedMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.RegisteringServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.MemberRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public final class UpdateExistingMember {

    private RegisteringServiceImpl registeringService;
    private String existingMemberId;

    @Before
    public void setUp() {
        MemberRepository memberRepository = new MemberRepositoryMemory();
        MemberFactory memberFactory = new MemberFactory();
        MemberDTOAssembler memberDTOAssembler = new MemberDTOAssembler();
        registeringService = new RegisteringServiceImpl(memberRepository, memberFactory, memberDTOAssembler);

        existingMemberId = registeringService.registerNewMember(new ColinHartMemberDTO(null));
    }

    @Test
    public void update_details_of_existing_member() {
        MemberDTO alisonMarlowMemberDTO = new AlisonMarlowMemberDTO(null);
        String newFirstName = alisonMarlowMemberDTO.getFirstName();
        String newLastName = alisonMarlowMemberDTO.getLastName();
        String newAddressLine1 = alisonMarlowMemberDTO.getAddressLine1();
        String newAddressLine2 = alisonMarlowMemberDTO.getAddressLine2();
        String newCity = alisonMarlowMemberDTO.getCity();
        String newCounty = alisonMarlowMemberDTO.getCounty();
        String newPostcode = alisonMarlowMemberDTO.getPostcode();
        String newTelephone = alisonMarlowMemberDTO.getContactNumber();
        String newEmail = alisonMarlowMemberDTO.getEmail();

        registeringService.updateExistingMember(new MemberDTO(
                existingMemberId,
                newFirstName, newLastName,
                newAddressLine1, newAddressLine2, newCity, newCounty, newPostcode,
                newTelephone, newEmail));
        MemberDTO memberDTO = registeringService.fetchMember(existingMemberId);

        assertThat(memberDTO.getMemberId(),
                is(equalTo(existingMemberId)));

        assertThat(memberDTO.getFirstName(),
                is(equalTo(newFirstName)));

        assertThat(memberDTO.getLastName(),
                is(equalTo(newLastName)));

        assertThat(memberDTO.getAddressLine1(),
                is(equalTo(newAddressLine1)));

        assertThat(memberDTO.getAddressLine2(),
                is(equalTo(newAddressLine2)));

        assertThat(memberDTO.getCity(),
                is(equalTo(newCity)));

        assertThat(memberDTO.getCounty(),
                is(equalTo(newCounty)));

        assertThat(memberDTO.getPostcode(),
                is(equalTo(newPostcode)));

        assertThat(memberDTO.getContactNumber(),
                is(equalTo(newTelephone)));

        assertThat(memberDTO.getEmail(),
                is(equalTo(newEmail)));
    }
}
