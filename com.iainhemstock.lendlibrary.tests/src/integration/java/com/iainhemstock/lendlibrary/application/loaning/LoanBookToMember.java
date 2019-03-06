package com.iainhemstock.lendlibrary.application.loaning;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.application.loaning.impls.LoaningServiceImpl;
import com.iainhemstock.lendlibrary.application.loaning.impls.assembler.LoanDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanId;
import com.iainhemstock.lendlibrary.domain.service.impls.ClockImpl;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.LoanRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class LoanBookToMember {

    private LoaningService loaningService;
    private TestableLoanRepositoryMemory loanRepository;
    private TestableClockImpl clock;
    private LoanDTOAssembler loanDTOAssembler;

    @Before
    public void setUp() {
        loanRepository = new TestableLoanRepositoryMemory();
        clock = new TestableClockImpl();
        loanDTOAssembler = new LoanDTOAssembler();
        loaningService = new LoaningServiceImpl(loanRepository, clock, loanDTOAssembler);
    }

    @Test
    public void loan_book_to_member() {

        String loanId = loaningService.loanBookToMember("id-1234", "id-5678");

        List<LoanDTO> outstandingLoansForMember = loaningService.fetchOutstandingLoansForMember("id-5678");

        assertThat(loanId,
                is(equalTo(loanRepository.getLastId().toString())));

        assertThat(outstandingLoansForMember,
                is(equalTo(List.of(new LoanDTO(
                        loanId,
                        "id-1234",
                        "id-5678",
                        clock.now(),
                        clock.datePlusDays(clock.now(), Loan.DEFAULT_LENGTH_IN_DAYS))))));
    }

    private class TestableLoanRepositoryMemory extends  LoanRepositoryMemory {
        private LoanId loanId;

        @Override
        public LoanId nextId() {
            return loanId = new LoanId(super.generateUniqueId());
        }

        public LoanId getLastId() {
            return loanId;
        }
    }

    private class TestableClockImpl extends ClockImpl {
        private Calendar calendar = Calendar.getInstance();

        @Override
        public Date now() {
            calendar.set(2019, Calendar.JANUARY, 1);
            return calendar.getTime();
        }

        @Override
        public Date datePlusDays(Date date, int numDays) {
            calendar.set(2019, Calendar.JANUARY, 15);
            return calendar.getTime();
        }
    }
}
