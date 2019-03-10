package com.iainhemstock.lendlibrary.application.loaning;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.application.loaning.impls.LoaningServiceImpl;
import com.iainhemstock.lendlibrary.application.loaning.impls.assembler.LoanDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanId;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanRepository;
import com.iainhemstock.lendlibrary.domain.model.loan.RentalPeriod;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.service.Clock;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public final class LoaningServiceShould {
    private Calendar calendar;
    private LoaningServiceImpl loaningService;
    private LoanRepository loanRepository;
    private Clock clock;

    @Before
    public void setUp() throws Exception {
        loanRepository = mock(LoanRepository.class);
        clock = mock(Clock.class);
        loaningService = new LoaningServiceImpl(loanRepository, clock);
        calendar = Calendar.getInstance();
        calendar.clear();
    }

    @Test
    public void throw_when_trying_to_loan_book_with_null_id() {
        try {
            loaningService.loanBookToMember(null, "id-5678");
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_loan_book_to_member_with_null_id() {
        try {
            loaningService.loanBookToMember("id-1234", null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_finalize_a_loan_with_null_id() {
        try {
            loaningService.finaliseBookLoan(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Loan id is required")));
        }
    }

    @Test
    public void loan_book_to_member() {

        LoanId loanId = new LoanId("id-abcd");

        when(loanRepository.nextId())
                .thenReturn(loanId);

        when(clock.now())
                .thenReturn(getLoanStartDate());

        when(clock.datePlusDays(getLoanStartDate(),14))
                .thenReturn(getLoanReturnDate());

        String actualLoanId = loaningService.loanBookToMember("id-1234", "id-5678");

        assertThat(actualLoanId,
                is(equalTo(loanId.toString())));

        verify(loanRepository)
                .add(makeLoanToBeAdded());
    }

    private Loan makeLoanToBeAdded() {
        return new Loan(
                new LoanId("id-abcd"),
                new BookId("id-1234"),
                new MemberId("id-5678"),
                new RentalPeriod(getLoanStartDate()));
    }

    @Test
    public void throw_when_trying_to_fetch_all_outstanding_loans_of_member_with_null_id() {
        try {
            loaningService.fetchOutstandingLoansForMember(null);
            fail("expected method under test to throw NullPointerException bit it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void fetch_all_outstanding_loans_of_member() {
        when(loanRepository.findLoansByMember(new MemberId("id-5678")))
                .thenReturn(List.of(
                        new Loan(
                                new LoanId("id-abcd"),
                                new BookId("id-1234"),
                                new MemberId("id-5678"),
                                new RentalPeriod(getLoanStartDate()))));

        List<LoanDTO> outstandingLoansForMember = loaningService.fetchOutstandingLoansForMember("id-5678");

        assertThat(outstandingLoansForMember,
                is(equalTo(getExpectedOutstandingLoans())));
    }

    private Date getLoanStartDate() {
        calendar.set(2019, Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    private Date getLoanReturnDate() {
        calendar.set(2019, Calendar.JANUARY, 15);
        return calendar.getTime();
    }

    private Date getLoanActualReturnDate() {
        calendar.set(2019, Calendar.JANUARY, 10);
        return calendar.getTime();
    }

    private List<LoanDTO> getExpectedOutstandingLoans() {
        return List.of(new LoanDTO(
                "id-abcd",
                "id-1234",
                "id-5678",
                getLoanStartDate(),
                getLoanReturnDate()));
    }

    @Test
    public void process_return_of_book_loaned_to_member() {

        LoanId loanId = new LoanId("id-abcd");

        when(loanRepository.getById(loanId))
                .thenReturn(makeLoanToBeAdded());

        when(clock.now())
                .thenReturn(getLoanActualReturnDate());

        loaningService.finaliseBookLoan(loanId.toString());

        Loan finalizedLoan = getFinalizedLoan(loanId);

        verify(loanRepository)
                .update(finalizedLoan);
    }

    private Loan getFinalizedLoan(LoanId loanId) {
        BookId bookId = new BookId("id-1234");
        MemberId memberId = new MemberId("id-5678");
        RentalPeriod rentalPeriod = new RentalPeriod(getLoanStartDate());
        rentalPeriod.setActualReturnDate(getLoanActualReturnDate());
        return new Loan(loanId, bookId, memberId, rentalPeriod);
    }

    @Test
    public void throw_when_trying_to_fetch_loan_history_of_member_with_null_id() {
        try {
            loaningService.fetchLoanHistoryForMember(null);
            fail("expected method under test to throw NullPointerException bit it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }
}
