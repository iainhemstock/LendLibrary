package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationNotFoundException;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReservationRepositoryMemory extends ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public boolean contains(Reservation reservation) {
        return reservations.contains(reservation);
    }

    @Override
    public void add(Reservation reservation) {
        Reservation copy = new Reservation(reservation);
        reservations.add(copy);
    }

    @Override
    public void remove(Reservation reservation) {
        reservations.remove(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservations;
    }

    @Override
    public Reservation getById(ReservationId reservationIdId) {
        return reservations.stream()
                .filter(reservation -> reservation.getId().equals(reservationIdId))
                .findFirst()
                .orElseThrow(getReservationNotFoundException(reservationIdId));
    }

    private Supplier<ReservationNotFoundException> getReservationNotFoundException(ReservationId reservationId) {
        return () -> new ReservationNotFoundException(
                "Reservation with id <" + reservationId.toString() + "> could not be found");
    }

    @Override
    public void update(Reservation reservation) {
        int index = IntStream.range(0, reservations.size())
                .filter(i -> reservations.get(i).equals(reservation))
                .findFirst()
                .orElseThrow(getReservationNotFoundException(reservation.getId()));

        reservations.set(index, reservation);
    }

    @Override
    public List<Reservation> findReservationsByMember(MemberId memberId) {
        return reservations.stream()
                .filter(reservation -> reservation.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findReservationsByBook(BookId bookId) {
        return reservations.stream()
                .filter(reservation -> reservation.getBookId().equals(bookId))
                .collect(Collectors.toList());
    }
}
