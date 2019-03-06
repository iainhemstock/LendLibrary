package com.iainhemstock.lendlibrary.application.printing;

import com.iainhemstock.lendlibrary.application.printing.impls.MemberCardPrintingServiceImpl;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberNotFoundException;
import com.iainhemstock.lendlibrary.domain.model.member.MemberRepository;
import com.iainhemstock.lendlibrary.domain.model.membercard.MemberCard;
import com.iainhemstock.lendlibrary.domain.service.MemberCardPrinter;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class MemberCardPrintingServiceShould {

    @Test
    public void throw_when_initialized_with_null_printer() {
        try {
            new MemberCardPrintingServiceImpl(null, mock(MemberRepository.class));
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member card printer is required")));
        }
    }

    @Test
    public void throw_when_initialized_with_null_repository() {
        try {
            new MemberCardPrintingServiceImpl(new MemberCardPrinterDummy(), null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member repository is required")));
        }
    }

    @Test
    public void throw_when_attempting_to_print_card_with_null_member_id() {
        MemberCardPrintingService memberCardPrintingService =
                new MemberCardPrintingServiceImpl(new MemberCardPrinterDummy(), mock(MemberRepository.class));
        try {
            memberCardPrintingService.printMemberCard(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test (expected = MemberNotFoundException.class)
    public void throw_when_attempting_to_print_card_of_non_existent_member() {
        MemberId nonExistentMemberId = new MemberId("id-1234");

        MemberRepository memberRepository = mock(MemberRepository.class);
        doThrow(new MemberNotFoundException(""))
                .when(memberRepository).getById(nonExistentMemberId);

        MemberCardPrintingService memberCardPrintingService =
                new MemberCardPrintingServiceImpl(new MemberCardPrinterDummy(), memberRepository);

        memberCardPrintingService.printMemberCard(nonExistentMemberId.toString());
    }

    private class MemberCardPrinterDummy implements MemberCardPrinter {
        @Override
        public void printMemberCard(MemberCard memberCard) {
            throw new UnsupportedOperationException("expected never to be called");
        }
    }
}
