package com.iainhemstock.lendlibrary.application.reserving;

import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.application.reserving.impls.ReservingServiceImpl;
import com.iainhemstock.lendlibrary.domain.service.impls.ClockImpl;
import com.iainhemstock.lendlibrary.infrastructure.persistence.memory.ReservationRepositoryMemory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class RemoveBookReservation {
    private ReservingService reservingService;

    @Before
    public void setUp() throws Exception {
        reservingService = new ReservingServiceImpl(
                new ReservationRepositoryMemory(), new ClockImpl());
    }

    @Test
    public void remove_existing_book_reservation_by_id() {
        String rid1 = reservingService.reserveBookForMember("id-1234", "id-5678");
        String rid2 = reservingService.reserveBookForMember("id-2345", "id-5678");
        String rid3 = reservingService.reserveBookForMember("id-7890", "id-5678");
        String rid4 = reservingService.reserveBookForMember("id-9876", "id-5678");

        reservingService.removeBookReservation(rid3);

        List<ReservationDTO> reservationDTOS = reservingService.fetchReservationsByMember("id-5678");

        assertThat(reservingService.fetchReservationsByMember("id-5678")
                , is(equalTo(List.of(
                        new ReservationDTO(rid1, "id-1234", "id-5678",
                                reservationDTOS.get(0).getReservationDate()),
                        new ReservationDTO(rid2, "id-2345", "id-5678",
                                reservationDTOS.get(1).getReservationDate()),
                        new ReservationDTO(rid4, "id-9876", "id-5678",
                                reservationDTOS.get(2).getReservationDate())))));
    }
}
