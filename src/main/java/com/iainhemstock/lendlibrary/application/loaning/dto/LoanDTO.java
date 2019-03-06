package com.iainhemstock.lendlibrary.application.loaning.dto;

import java.util.Date;
import java.util.Objects;

public class LoanDTO {
    private final String loanId;
    private final String bookId;
    private final String memberId;
    private final Date beginDate;
    private final Date expectedReturnDate;
    private final Date actualReturnDate;

    public LoanDTO(String loanId, String bookId, String memberId, Date beginDate, Date expectedReturnDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.beginDate = beginDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = null;
    }

    public LoanDTO(
            String loanId,
            String bookId,
            String memberId,
            Date beginDate,
            Date expectedReturnDate,
            Date actualReturnDate) {

        this.loanId = loanId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.beginDate = beginDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
    }

    public String getLoanId() {
        return this.loanId;
    }

    public Date getStartDate() {
        return this.beginDate;
    }

    public Date getExpectedReturnDate() {
        return this.expectedReturnDate;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "loanId='" + loanId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", beginDate=" + beginDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDTO loanDTO = (LoanDTO) o;
        return Objects.equals(loanId, loanDTO.loanId) &&
                Objects.equals(bookId, loanDTO.bookId) &&
                Objects.equals(memberId, loanDTO.memberId) &&
                Objects.equals(beginDate, loanDTO.beginDate) &&
                Objects.equals(expectedReturnDate, loanDTO.expectedReturnDate) &&
                Objects.equals(actualReturnDate, loanDTO.actualReturnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, bookId, memberId, beginDate, expectedReturnDate, actualReturnDate);
    }
}
