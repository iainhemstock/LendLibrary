package com.iainhemstock.lendlibrary.domain.model.loan;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public final class RentalPeriod {
    private static final int STANDARD_RENTAL_LENGTH_IN_DAYS = 14;

    private final Date begin;
    private final Date expectedReturn;
    private Date actualReturn;

    public RentalPeriod(Date begin) {
        this.begin = begin;
        this.expectedReturn = calculateReturnDate();
    }

    public RentalPeriod(RentalPeriod copy) {
        this.begin = new Date(copy.begin.getTime());
        this.expectedReturn = new Date(copy.expectedReturn.getTime());
        if (copy.actualReturn != null)
            this.actualReturn = new Date(copy.actualReturn.getTime());
    }

    private Date calculateReturnDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.begin);
        calendar.add(Calendar.DATE, STANDARD_RENTAL_LENGTH_IN_DAYS);
        return calendar.getTime();
    }

    public Date getBeginDate() {
        return this.begin;
    }

    public Date getExpectedReturnDate() {
        return this.expectedReturn;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturn = actualReturnDate;
    }

    public Date getActualReturnDate() {
        return this.actualReturn;
    }

    @Override
    public String toString() {
        return "RentalPeriod{" +
                "begin=" + begin +
                ", expectedReturn=" + expectedReturn +
                ", actualReturn=" + actualReturn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalPeriod that = (RentalPeriod) o;
        return Objects.equals(begin, that.begin) &&
                Objects.equals(expectedReturn, that.expectedReturn) &&
                Objects.equals(actualReturn, that.actualReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(begin, expectedReturn, actualReturn);
    }
}
