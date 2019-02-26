package com.iainhemstock.lendlibrary.application.printing;

import com.iainhemstock.lendlibrary.application.printing.impls.MemberCardPrintingServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.RegisteringService;
import com.iainhemstock.lendlibrary.application.registering.dto.ColinHartMemberDTO;
import com.iainhemstock.lendlibrary.application.registering.dto.MemberDTO;
import com.iainhemstock.lendlibrary.application.registering.impls.RegisteringServiceImpl;
import com.iainhemstock.lendlibrary.application.registering.impls.assembler.MemberDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.member.*;
import com.iainhemstock.lendlibrary.domain.model.membercard.MemberCard;
import com.iainhemstock.lendlibrary.domain.model.membercard.MemberCardPrinter;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.MemberRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public final class PrintMemberCard {

    private MemberCardPrintingService memberCardPrintingService;
    private RegisteringService registeringService;
    private MemberCardPrinter memberCardPrinterSpy;
    private MemberDTO memberDTO;
    private String existingMemberId;
    private MemberRepository memberRepository;

    @Before
    public void setUp() throws Exception {
        memberDTO = new ColinHartMemberDTO(null);
        memberRepository = new MemberRepositoryMemory();

        registeringService = new RegisteringServiceImpl(memberRepository,
                new MemberFactory(), new MemberDTOAssembler());

        memberCardPrinterSpy = new MemberCardPrinterSpy();
        memberCardPrintingService = new MemberCardPrintingServiceImpl(memberCardPrinterSpy, memberRepository);
    }

    @Test
    public void print_member_card() {
        existingMemberId = registeringService.registerNewMember(memberDTO);

        memberCardPrintingService.printMemberCard(existingMemberId);

        verifyThatPrintMethodWasCalled();
        assertThatExpectedMemberCardWasPrinted();
    }

    private void verifyThatPrintMethodWasCalled() {
        assertTrue("expected MemberCardPrinter::print(expectedMemberCardToBePrinted) to be called but it wasn't",
                ((MemberCardPrinterSpy) memberCardPrinterSpy).printWasCalled);
    }

    private void assertThatExpectedMemberCardWasPrinted() {
        MemberCard expectedMemberCardToBePrinted = new MemberCard(
                new MemberId(existingMemberId),
                new FullName(new FirstName(memberDTO.getFirstName()), new LastName(memberDTO.getLastName())));

        assertThat("expected printed member card to be " + expectedMemberCardToBePrinted + " but it wasn't",
                ((MemberCardPrinterSpy) memberCardPrinterSpy).memberCardToPrint,
                is(equalTo(expectedMemberCardToBePrinted)));
    }

    private class MemberCardPrinterSpy implements MemberCardPrinter {
        boolean printWasCalled = false;
        MemberCard memberCardToPrint;

        @Override
        public void printMemberCard(MemberCard memberCard) {
            printWasCalled = true;
            memberCardToPrint = memberCard;
        }
    }
}
