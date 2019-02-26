package com.iainhemstock.lendlibrary.domain.model.reservation;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.shared.Repository;

import java.util.List;

public abstract class ReservationRepository extends Repository<Reservation> {

    public ReservationId nextId() {
        return new ReservationId(super.generateUniqueId());
    }

    public abstract Reservation getById(final ReservationId reservationId);

    public abstract List<Reservation> findReservationsByMember(final MemberId memberId);

    public abstract List<Reservation> findReservationsByBook(final BookId bookId);
}
