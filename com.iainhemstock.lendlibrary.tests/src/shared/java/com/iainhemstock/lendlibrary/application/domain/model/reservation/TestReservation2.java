package com.iainhemstock.lendlibrary.application.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;

import java.util.Date;

public final class TestReservation2 extends Reservation {

    public TestReservation2(final String reservationId) {
        super(
                new ReservationId(reservationId),
                new BookId("id-2345"),
                new MemberId("id-6789"),
                new Date());
    }
}
