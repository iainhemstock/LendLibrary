package com.iainhemstock.lendlibrary.application.registering;

import com.iainhemstock.lendlibrary.application.registering.dto.AlisonMarlowMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.RegisteringServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.Member;
import com.iainhemstock.lendlibrary.domain.model.member.MemberFactory;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.MemberRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class RegisterNewMember {

    private RegisteringService registeringService;
    private TestableMemberRepository testableMembershipRepository;

    @Before
    public void setUp() {
        testableMembershipRepository = new TestableMemberRepository();
        MemberFactory memberFactory = new MemberFactory();
        MemberDTOAssembler memberDTOAssembler = new MemberDTOAssembler();
        registeringService = new RegisteringServiceImpl(
                testableMembershipRepository, memberFactory, memberDTOAssembler);
    }

    @Test
    public void register_new_member() {
        final MemberDTO alisonMarlowMemberDTO = new AlisonMarlowMemberDTO(null);

        final String alisonMarlowMembershipId = registeringService.registerNewMember(alisonMarlowMemberDTO);
        final MemberDTO fetchedMemberDTO = registeringService.fetchMember(alisonMarlowMembershipId);
        final List<MemberDTO> allFetchedMemberDTOs = registeringService.fetchAllMembers();

        assertThat(alisonMarlowMembershipId,
                is(equalTo(testableMembershipRepository.getLastId().toString())));

        assertThat(fetchedMemberDTO.getMemberId(),
                is(equalTo(alisonMarlowMembershipId)));

        assertThat(fetchedMemberDTO.getFirstName(),
                is(equalTo(alisonMarlowMemberDTO.getFirstName())));

        assertThat(fetchedMemberDTO.getLastName(),
                is(equalTo(alisonMarlowMemberDTO.getLastName())));

        assertThat(fetchedMemberDTO.getAddressLine1(),
                is(equalTo(alisonMarlowMemberDTO.getAddressLine1())));

        assertThat(fetchedMemberDTO.getAddressLine2(),
                is(equalTo(alisonMarlowMemberDTO.getAddressLine2())));

        assertThat(fetchedMemberDTO.getCity(),
                is(equalTo(alisonMarlowMemberDTO.getCity())));

        assertThat(fetchedMemberDTO.getCounty(),
                is(equalTo(alisonMarlowMemberDTO.getCounty())));

        assertThat(fetchedMemberDTO.getPostcode(),
                is(equalTo(alisonMarlowMemberDTO.getPostcode())));

        assertThat(fetchedMemberDTO.getContactNumber(),
                is(equalTo(alisonMarlowMemberDTO.getContactNumber())));

        assertThat(fetchedMemberDTO.getEmail(),
                is(equalTo(alisonMarlowMemberDTO.getEmail())));

        assertThat(allFetchedMemberDTOs.get(0),
                is(equalTo(fetchedMemberDTO)));
    }

    private class TestableMemberRepository extends MemberRepositoryMemory {
        private MemberId memberId;

        @Override
        public MemberId nextId() {
            return memberId = new MemberId(UUID.randomUUID().toString().replace("-", ""));
        }

        public MemberId getLastId() {
            return memberId;
        }
    }
}
