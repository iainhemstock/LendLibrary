package com.iainhemstock.lendlibrary.application.registering.impls.assembler;

import com.iainhemstock.lendlibrary.application.registering.dto.MembershipDTO;
import com.iainhemstock.lendlibrary.domain.model.member.Membership;

import java.util.ArrayList;
import java.util.List;

public class MembershipDTOAssembler {

    public List<MembershipDTO> toDTOList(List<Membership> memberships) {
        List<MembershipDTO> dtos = new ArrayList<>();

        for (Membership membership : memberships) {

            final MembershipDTO dto = new MembershipDTO(
                    membership.getId().toString(),
                    membership.getFullName().getFirstName().toString(),
                    membership.getFullName().getLastName().toString(),
                    membership.getAddress().getAddressLine1().toString(),
                    membership.getAddress().getAddressLine2().toString(),
                    membership.getAddress().getCity().toString(),
                    membership.getAddress().getCounty().toString(),
                    membership.getAddress().getPostcode().toString(),
                    membership.getContactDetails().getTelephone().toString(),
                    membership.getContactDetails().getEmail().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public MembershipDTO toDTO(Membership membership) {
        return toDTOList(List.of(membership)).get(0);
    }
}
