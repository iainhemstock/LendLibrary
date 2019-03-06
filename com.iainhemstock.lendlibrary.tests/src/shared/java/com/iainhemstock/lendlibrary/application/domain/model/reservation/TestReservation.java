package com.iainhemstock.lendlibrary.application.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;

import java.util.Date;

public final class TestReservation extends Reservation {

    public TestReservation(final String reservationId) {
        super(
                new ReservationId(reservationId),
                new BookId("id-1234"),
                new MemberId("id-5678"),
                new Date());
    }
}
