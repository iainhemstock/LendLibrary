package com.iainhemstock.lendlibrary.application.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;

import java.util.Date;

public final class TestReservation3 extends Reservation {

    public TestReservation3(final String reservationId) {
        super(
                new ReservationId(reservationId),
                new BookId("id-3456"),
                new MemberId("id-7890"),
                new Date());
    }
}
