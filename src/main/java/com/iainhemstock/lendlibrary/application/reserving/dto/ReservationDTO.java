package com.iainhemstock.lendlibrary.application.reserving.dto;

import java.util.Date;
import java.util.Objects;

public final class ReservationDTO {

    private final String reservationId;
    private final String bookId;
    private final String memberId;
    private final Date reservationDate;

    public ReservationDTO(String reservationId, String bookId, String memberId, Date reservationDate) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public String getBookId() {
        return this.bookId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId='" + reservationId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", reservationDate=" + reservationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDTO that = (ReservationDTO) o;
        return Objects.equals(reservationId, that.reservationId) &&
                Objects.equals(bookId, that.bookId) &&
                Objects.equals(memberId, that.memberId) &&
                Objects.equals(reservationDate, that.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, bookId, memberId, reservationDate);
    }
}
