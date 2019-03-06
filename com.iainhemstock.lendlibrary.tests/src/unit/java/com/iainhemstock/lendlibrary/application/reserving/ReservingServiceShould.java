package com.iainhemstock.lendlibrary.application.reserving;

import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.application.reserving.impls.ReservingServiceImpl;
import com.iainhemstock.lendlibrary.application.reserving.impls.assembler.ReservationDTOAssembler;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import com.iainhemstock.lendlibrary.domain.service.Clock;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public final class ReservingServiceShould {

    private ReservingServiceImpl reservingService;
    private ReservationRepository reservationRepository;
    private Clock clock;

    @Before
    public void setUp() throws Exception {
        reservationRepository = mock(ReservationRepository.class);
        clock = mock(Clock.class);

        reservingService = new ReservingServiceImpl(
                reservationRepository,
                clock,
                new ReservationDTOAssembler());
    }

    @Test
    public void throw_when_trying_reserve_book_with_null_id() {
        try {
            reservingService.reserveBookForMember(null, "id-5678");
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book id is required")));
        }
    }

    @Test
    public void throw_when_trying_reserve_book_for_member_with_null_id() {
        try {
            reservingService.reserveBookForMember("id-1234", null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void reserve_book_for_member() {
        ReservationId reservationId = new ReservationId("id-abcd");
        BookId bookId = new BookId ("id-1234");
        MemberId memberId = new MemberId("id-5678");

        when(reservationRepository.nextId())
                .thenReturn(reservationId);

        when(clock.now())
                .thenReturn(makeReservationDate());

        String actualReservationId = reservingService.reserveBookForMember(
                bookId.toString(),
                memberId.toString());

        assertThat(actualReservationId,
                is(equalTo(reservationId.toString())));

        verify(reservationRepository).add(
                new Reservation(reservationId, bookId, memberId, makeReservationDate()));
    }

    @Test
    public void fetch_all_reservations_by_member() {
        ReservationId reservationId = new ReservationId("id-abcd");
        BookId bookId = new BookId("id-1234");
        MemberId memberId = new MemberId("id-5678");

        when(reservationRepository.findReservationsByMember(memberId))
                .thenReturn(List.of(new Reservation(reservationId, bookId, memberId, makeReservationDate())));

        List<ReservationDTO> reservationsByMember = reservingService.fetchReservationsByMember(memberId.toString());

        assertThat(reservationsByMember,
                is(equalTo(List.of(new ReservationDTO(
                        reservationId.toString(),
                        bookId.toString(),
                        memberId.toString(),
                        makeReservationDate())))));
    }

    @Test
    public void fetch_all_reservations_by_book() {
        ReservationId reservationId = new ReservationId("id-abcd");
        BookId bookId = new BookId("id-1234");
        MemberId memberId = new MemberId("id-5678");

        when(reservationRepository.findReservationsByBook(bookId))
                .thenReturn(List.of(new Reservation(reservationId, bookId, memberId, makeReservationDate())));

        List<ReservationDTO> reservationsByBook = reservingService.fetchReservationsByBook(bookId.toString());

        assertThat(reservationsByBook,
                is(equalTo(List.of(new ReservationDTO(
                        reservationId.toString(),
                        bookId.toString(),
                        memberId.toString(),
                        makeReservationDate())))));
    }

    private Date makeReservationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2019, Calendar.APRIL, 15);
        return calendar.getTime();
    }

    @Test
    public void throw_when_trying_to_fetch_all_reservations_of_member_with_null_id() {
        try {
            reservingService.fetchReservationsByMember(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Member id is required")));
        }
    }

    @Test
    public void throw_when_trying_to_fetch_all_reservations_by_book_with_null_id() {
        try {
            reservingService.fetchReservationsByBook(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Book id is required")));
        }
    }

    @Test
    public void throw_when_trying_remove_reservation_with_null_id() {
        try {
            reservingService.removeBookReservation(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("Reservation id is required")));
        }
    }

    @Test
    public void remove_book_reservation_by_its_id() {

        Reservation reservation = new Reservation(
                new ReservationId("id-abcd"), new BookId("id-1234"), new MemberId("id-5678"), new Date());

        when(reservationRepository.getById(new ReservationId("id-abcd")))
                .thenReturn(reservation);

        reservingService.removeBookReservation("id-abcd");

        verify(reservationRepository).remove(reservation);
    }
}
