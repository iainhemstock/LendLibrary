package com.iainhemstock.lendlibrary.domain.model.loan;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Date;
import java.util.Objects;

public class Loan implements Entity {

    public static final int DEFAULT_LENGTH_IN_DAYS = 14;

    private final LoanId loanId;
    private final BookId bookId;
    private final MemberId memberId;
    private final RentalPeriod rentalPeriod;

    public Loan(LoanId loanId, BookId bookId, MemberId memberId, RentalPeriod rentalPeriod) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.rentalPeriod = rentalPeriod;
    }

    public Loan(Loan copy) {
        this.loanId = copy.loanId;
        this.bookId = copy.bookId;
        this.memberId = copy.memberId;
        this.rentalPeriod = new RentalPeriod(copy.rentalPeriod);
    }

    @Override
    public LoanId getId() {
        return this.loanId;
    }

    public BookId getBookId() {
        return this.bookId;
    }

    public MemberId getMemberId() {
        return this.memberId;
    }

    public Date getStartDate() {
        return this.rentalPeriod.getBeginDate();
    }

    public Date getExpectedReturnDate() {
        return this.rentalPeriod.getExpectedReturnDate();
    }

    public Date getActualReturnDate() {
        return this.rentalPeriod.getActualReturnDate();
    }

    public void finalise(Date now) {
        this.rentalPeriod.setActualReturnDate(now);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", rentalPeriod=" + rentalPeriod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return Objects.equals(loanId, ((Loan) o).loanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }
}
