package com.iainhemstock.lendlibrary.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Date;
import java.util.Objects;

public class Reservation implements Entity {
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

    public Reservation(Reservation copy) {
        this.reservationId = copy.reservationId;
        this.bookId = copy.bookId;
        this.memberId = copy.memberId;
        this.reservationDate = new Date(copy.reservationDate.getTime());
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
        if (o == null) return false;
        return Objects.equals(reservationId, ((Reservation) o).reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, bookId, memberId, reservationDate);
    }
}
