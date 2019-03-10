package com.iainhemstock.lendlibrary.application.reserving.impls;

import com.iainhemstock.lendlibrary.application.reserving.ReservingService;
import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.application.reserving.impls.assembler.ReservationDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import com.iainhemstock.lendlibrary.domain.service.Clock;

import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class ReservingServiceImpl implements ReservingService {
    private ReservationRepository reservationRepository;
    private Clock clock;

    public ReservingServiceImpl(ReservationRepository reservationRepository, Clock clock) {
        this.reservationRepository = reservationRepository;
        this.clock = clock;
        requireNonNull(this.reservationRepository, "Reservation repository is required");
        requireNonNull(this.clock, "Clock is required");
    }

    @Override
    public String reserveBookForMember(String bookId, String memberId) {
        requireNonNull(bookId, "Book id is required");
        requireNonNull(memberId, "Member id is required");

        Reservation reservation = new Reservation(
                reservationRepository.nextId(), new BookId(bookId), new MemberId(memberId), nowDate());

        reservationRepository.add(reservation);

        return reservation.getId().toString();
    }

    @Override
    public void removeBookReservation(String reservationId) {
        requireNonNull(reservationId, "Reservation id is required");
        Reservation reservation = reservationRepository.getById(new ReservationId(reservationId));
        reservationRepository.remove(reservation);
    }

    private Date nowDate() {
        return clock.now();
    }

    @Override
    public List<ReservationDTO> fetchReservationsByMember(String memberId) {
        requireNonNull(memberId, "Member id is required");
        List<Reservation> reservationsByMember = reservationRepository.findReservationsByMember(new MemberId(memberId));
        ReservationDTOAssembler assembler = new ReservationDTOAssembler();
        return assembler.toDTOList(reservationsByMember);
    }

    @Override
    public List<ReservationDTO> fetchReservationsByBook(String bookId) {
        requireNonNull(bookId, "Book id is required");
        List<Reservation> reservationsByBook = reservationRepository.findReservationsByBook(new BookId(bookId));
        ReservationDTOAssembler assembler = new ReservationDTOAssembler();
        return assembler.toDTOList(reservationsByBook);
    }
}
