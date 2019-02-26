package com.iainhemstock.lendlibrary.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Date;
import java.util.Objects;

public final class Reservation implements Entity {
    private final ReservationId reservationId;
    private final BookId bookId;
    private final MemberId memberId;
    private Date reservationDate;

    public Reservation(ReservationId reservationId, BookId bookId, MemberId memberId, Date reservationDate) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.reservationDate = reservationDate;
    }

    @Override
    public ReservationId getId() {
        return this.reservationId;
    }

    public BookId getBookId() {
        return this.bookId;
    }

    public MemberId getMemberId() {
        return this.memberId;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
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
