package com.iainhemstock.lendlibrary.application.registering;

import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.domain.model.member.FullName;

import java.util.List;

public interface RegisteringService {

    String registerNewMember(MemberDTO memberDTO);

    void updateExistingMember(MemberDTO updatedMemberDTO);

    MemberDTO fetchMember(String memberId);

    List<MemberDTO> fetchAllMembers();

}