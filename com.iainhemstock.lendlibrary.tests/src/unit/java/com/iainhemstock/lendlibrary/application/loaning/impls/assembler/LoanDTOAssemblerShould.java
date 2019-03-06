package com.iainhemstock.lendlibrary.application.loaning.impls.assembler;

import com.iainhemstock.lendlibrary.application.loaning.dto.LoanDTO;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanId;
import com.iainhemstock.lendlibrary.domain.model.loan.RentalPeriod;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class LoanDTOAssemblerShould {
    private Calendar calendar = Calendar.getInstance();

    @Before
    public void setUp() throws Exception {
        calendar.clear();
    }

    @Test
    public void assemble_loans_list_into_loans_dto_list() {
        LoanDTOAssembler assembler = new LoanDTOAssembler();

        List<LoanDTO> loanDTOs = assembler.toDTOList(
                List.of(new Loan(
                                new LoanId("id-abcd"),
                                new BookId("id-1234"),
                                new MemberId("id-5678"),
                                new RentalPeriod(makeDate1())),
                        new Loan(
                                new LoanId("id-efgh"),
                                new BookId("id-2345"),
                                new MemberId("id-3456"),
                                new RentalPeriod(makeDate3()))));

        assertThat(loanDTOs,
                is(equalTo(List.of(
                        new LoanDTO(
                                "id-abcd",
                                "id-1234",
                                "id-5678",
                                makeDate1(),
                                makeDate2()),
                        new LoanDTO(
                                "id-efgh",
                                "id-2345",
                                "id-3456",
                                makeDate3(),
                                makeDate4())))));
    }

    private Date makeDate1() {
        calendar.set(2019, Calendar.JANUARY, 1);
        return calendar.getTime();
    }

    private Date makeDate2() {
        calendar.set(2019, Calendar.JANUARY, 15);
        return calendar.getTime();
    }

    private Date makeDate3() {
        calendar.set(2019, Calendar.FEBRUARY, 1);
        return calendar.getTime();
    }

    private Date makeDate4() {
        calendar.set(2019, Calendar.FEBRUARY, 15);
        return calendar.getTime();
    }
}
