package com.iainhemstock.lendlibrary.application.registering.impls;

import com.iainhemstock.lendlibrary.application.registering.RegisteringServiceAdapter;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class RegisteringServiceAdapterImpl implements RegisteringServiceAdapter {

    private MemberRepository memberRepository;
    private MemberFactory memberFactory;
    private MemberDTOAssembler memberDTOAssembler;

    public RegisteringServiceAdapterImpl(MemberRepository memberRepository, MemberFactory memberFactory,
                                         MemberDTOAssembler memberDTOAssembler) {
        this.memberRepository = memberRepository;
        this.memberFactory = memberFactory;
        this.memberDTOAssembler = memberDTOAssembler;
        requireNonNull(this.memberRepository, "Member repository is required");
        requireNonNull(this.memberFactory, "Member factory is required");
        requireNonNull(this.memberDTOAssembler, "Member DTO assembler is required");
    }

    @Override
    public String openMembership(MemberDTO memberDTO) {
        MemberId memberId = memberRepository.nextId();
        Member newMember = memberFactory.create(
                memberId.toString(),
                memberDTO.getFirstName(), memberDTO.getLastName(),
                memberDTO.getAddressLine1(), memberDTO.getAddressLine2(), memberDTO.getCity(),
                memberDTO.getCounty(), memberDTO.getPostcode(),
                memberDTO.getContactNumber(), memberDTO.getEmail());
        memberRepository.add(newMember);

        return memberId.toString();
    }

    @Override
    public List<MemberDTO> fetchAllMemberships() {
        List<Member> allMembers = memberRepository.getAll();
        return memberDTOAssembler.toDTOList(allMembers);
    }

    @Override
    public MemberDTO fetchMembership(String membershipId) throws MemberNotFoundException {
        requireNonNull(membershipId, "Member id is required");
        Member member = memberRepository.getById(new MemberId(membershipId));
        return memberDTOAssembler.toDTO(member);
    }
}
