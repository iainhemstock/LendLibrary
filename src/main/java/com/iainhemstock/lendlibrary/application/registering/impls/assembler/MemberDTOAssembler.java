package com.iainhemstock.lendlibrary.application.registering.impls.assembler;

import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.domain.model.member.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDTOAssembler {

    public List<MemberDTO> toDTOList(List<Member> members) {
        List<MemberDTO> dtos = new ArrayList<>();

        for (Member member : members) {

            final MemberDTO dto = new MemberDTO(
                    member.getId().toString(),
                    member.getFullName().getFirstName().toString(),
                    member.getFullName().getLastName().toString(),
                    member.getAddress().getAddressLine1().toString(),
                    member.getAddress().getAddressLine2().toString(),
                    member.getAddress().getCity().toString(),
                    member.getAddress().getCounty().toString(),
                    member.getAddress().getPostcode().toString(),
                    member.getContactDetails().getTelephone().toString(),
                    member.getContactDetails().getEmail().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public MemberDTO toDTO(final Member member) {
        return toDTOList(List.of(member)).get(0);
    }
}
