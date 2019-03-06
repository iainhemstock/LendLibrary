package com.iainhemstock.lendlibrary.application.reserving.impls.assembler;

import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.domain.model.book.BookId;
import com.iainhemstock.lendlibrary.domain.model.member.MemberId;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationId;
import com.iainhemstock.lendlibrary.domain.shared.Id;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class ReservationDTOAssemblerShould {

    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        calendar = Calendar.getInstance();
        calendar.clear();
    }

    @Test
    public void throw_when_reservation_list_is_null() {
        try {
            new ReservationDTOAssembler().toDTOList(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        } catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("List of reservations is required")));
        }
    }

    @Test
    public void assemble_reservation_list_into_reservation_DTO_list() {
        ReservationDTOAssembler assembler = new ReservationDTOAssembler();

        List<ReservationDTO> reservationDTOS = assembler.toDTOList(
                List.of(
                    new Reservation(
                            new ReservationId("id-abcd"),
                            new BookId("id-1234"),
                            new MemberId("id-5678"),
                            makeFirstReservationDate()),
                    new Reservation(
                            new ReservationId("id-efgh"),
                            new BookId("id-9012"),
                            new MemberId("id-3456"),
                            makeSecondReservationDate())));

        assertThat(reservationDTOS,
                is(equalTo(List.of(
                        new ReservationDTO(
                                "id-abcd",
                                "id-1234",
                                "id-5678",
                                makeFirstReservationDate()),
                        new ReservationDTO(
                                "id-efgh",
                                "id-9012",
                                "id-3456",
                                makeSecondReservationDate())))));
    }

    private Date makeFirstReservationDate() {
        calendar.set(2019, Calendar.APRIL, 15);
        return calendar.getTime();
    }

    private Date makeSecondReservationDate() {
        calendar.set(2019, Calendar.FEBRUARY, 4);
        return calendar.getTime();
    }
}
