package com.iainhemstock.lendlibrary.application.domain.model.loan;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.loan.Loan;
import com.iainhemstock.lendlibrary.domain.model.loan.LoanId;
import com.iainhemstock.lendlibrary.domain.model.loan.RentalPeriod;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;

import java.util.Calendar;
import java.util.Date;

public final class TestLoan extends Loan {

    public TestLoan() {
        super(new LoanId("id-abcd"),
                new BookId("id-1234"),
                new MemberId("id-5678"),
                new RentalPeriod(makeStartDate()));
    }

    private static Date makeStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2019, Calendar.JANUARY, 1, 12, 15, 0);
        return calendar.getTime();
    }
}
