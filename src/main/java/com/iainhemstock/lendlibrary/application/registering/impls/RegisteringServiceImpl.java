package com.iainhemstock.lendlibrary.application.registering.impls;

import com.iainhemstock.lendlibrary.application.registering.RegisteringService;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class RegisteringServiceImpl implements RegisteringService {

    private MemberRepository memberRepository;
    private MemberFactory memberFactory;
    private MemberDTOAssembler memberDTOAssembler;

    public RegisteringServiceImpl(MemberRepository memberRepository, MemberFactory memberFactory,
                                  MemberDTOAssembler memberDTOAssembler) {
        this.memberRepository = memberRepository;
        this.memberFactory = memberFactory;
        this.memberDTOAssembler = memberDTOAssembler;
        requireNonNull(this.memberRepository, "Member repository is required");
        requireNonNull(this.memberFactory, "Member factory is required");
        requireNonNull(this.memberDTOAssembler, "Member DTO assembler is required");
    }

    @Override
    public String registerNewMember(MemberDTO memberDTO) {
        MemberId memberId = memberRepository.nextId();
        Member newMember = memberFactory.create(
                memberId.toString(),
                memberDTO.getFirstName(),
                memberDTO.getLastName(),
                memberDTO.getAddressLine1(),
                memberDTO.getAddressLine2(),
                memberDTO.getCity(),
                memberDTO.getCounty(),
                memberDTO.getPostcode(),
                memberDTO.getContactNumber(),
                memberDTO.getEmail());

        memberRepository.add(newMember);

        return memberId.toString();
    }

    @Override
    public void updateExistingMember(MemberDTO memberDTO) {
        requireNonNull(memberDTO, "Member DTO is required");

        Member member = memberRepository.getById(new MemberId(memberDTO.getMemberId()));

        renameMember(member, memberDTO);
        relocateMember(member, memberDTO);
        updateContactDetails(member, memberDTO);

        memberRepository.update(member);
    }

    private void renameMember(Member member, MemberDTO memberDTO) {
        FullName updatedName = new FullName(
                new FirstName(memberDTO.getFirstName()), new LastName(memberDTO.getLastName()));
        member.renameTo(updatedName);
    }

    private void relocateMember(Member member, MemberDTO memberDTO) {
        Address relocatedAddress = new Address(
                new AddressLine1(memberDTO.getAddressLine1()),
                new AddressLine2(memberDTO.getAddressLine2()),
                new City(memberDTO.getCity()),
                new County(memberDTO.getCounty()),
                new Postcode(memberDTO.getPostcode()));
        member.relocateTo(relocatedAddress);
    }

    private void updateContactDetails(Member member, MemberDTO memberDTO) {
        ContactDetails updatedContactDetails = new ContactDetails(
                new Telephone(memberDTO.getContactNumber()),
                new Email(memberDTO.getEmail()));
        member.updateContactDetailsTo(updatedContactDetails);
    }

    @Override
    public List<MemberDTO> fetchAllMembers() {
        List<Member> allMembers = memberRepository.getAll();
        return memberDTOAssembler.toDTOList(allMembers);
    }

    @Override
    public MemberDTO fetchMember(String memberId) throws MemberNotFoundException {
        requireNonNull(memberId, "Member id is required");
        Member member = memberRepository.getById(new MemberId(memberId));
        return memberDTOAssembler.toDTO(member);
    }
}
