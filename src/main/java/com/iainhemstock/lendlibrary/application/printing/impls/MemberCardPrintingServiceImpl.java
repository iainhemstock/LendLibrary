package com.iainhemstock.lendlibrary.application.printing.impls;

import com.iainhemstock.lendlibrary.application.printing.MemberCardPrintingService;
import com.iainhemstock.lendlibrary.domain.model.member.Member;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberRepository;
import com.iainhemstock.lendlibrary.domain.model.membercard.MemberCard;
import com.iainhemstock.lendlibrary.domain.service.MemberCardPrinter;

import static java.util.Objects.requireNonNull;

public final class MemberCardPrintingServiceImpl implements MemberCardPrintingService {
    private MemberCardPrinter memberCardPrinter;
    private MemberRepository memberRepository;

    public MemberCardPrintingServiceImpl(MemberCardPrinter memberCardPrinter, MemberRepository memberRepository) {
        this.memberCardPrinter = memberCardPrinter;
        this.memberRepository = memberRepository;
        requireNonNull(this.memberCardPrinter, "Member card printer is required");
        requireNonNull(this.memberRepository, "Member repository is required");
    }

    @Override
    public void printMemberCard(String memberId) {
        requireNonNull(memberId, "Member id is required");

        Member member = memberRepository.getById(new MemberId(memberId));
        MemberCard memberCard = new MemberCard(new MemberId(memberId), member.getFullName());
        memberCardPrinter.printMemberCard(memberCard);
    }
}
