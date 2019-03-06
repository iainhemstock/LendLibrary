package com.iainhemstock.lendlibrary.application.loaning;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.application.loaning.impls.LoaningServiceImpl;
import com.iainhemstock.lendlibrary.application.loaning.impls.assembler.LoanDTOAssembler;
import com.iainhemstock.lendlibrary.domain.service.impls.ClockImpl;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.LoanRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class ReturnBookFromMember {

    private LoaningServiceImpl loaningService;
    private TestableClock clock;

    @Before
    public void setUp() throws Exception {
        clock = new TestableClock();
        loaningService = new LoaningServiceImpl(new LoanRepositoryMemory(), clock, new LoanDTOAssembler());

    }

    @Test
    public void process_the_return_of_book_loaned_to_member() {

        String bookId = "id-1234";
        String memberId = "id-5678";

        loaningService.loanBookToMember(bookId, memberId);

        List<LoanDTO> loanDTOS = loaningService.fetchOutstandingLoansForMember(memberId);
        String loanId = loanDTOS.get(0).getLoanId();
        Date beginDate = loanDTOS.get(0).getStartDate();
        Date expectedReturnDate = loanDTOS.get(0).getExpectedReturnDate();

        loaningService.finaliseBookLoan(loanId);

        assertThat(loaningService.fetchOutstandingLoansForMember(memberId),
                is(equalTo(LoaningService.NO_OUTSTANDING_LOANS)));

        assertThat(loaningService.fetchLoanHistoryForMember(memberId),
                is(equalTo(List.of(
                        new LoanDTO(
                                loanId,
                                bookId,
                                memberId,
                                beginDate,
                                expectedReturnDate,
                                clock.getLastDate())))));
    }

    private class TestableClock extends ClockImpl {
        private Date lastDate;

        @Override
        public Date now() {
            return lastDate = new Date();
        }

        public Date getLastDate() {
            return lastDate;
        }
    }
}
