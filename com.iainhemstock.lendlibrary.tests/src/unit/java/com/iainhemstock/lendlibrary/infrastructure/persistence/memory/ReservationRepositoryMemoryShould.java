package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.application.domain.model.reservation.TestReservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;
import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.ReservationRepositoryShould;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public final class ReservationRepositoryMemoryShould extends ReservationRepositoryShould {
    private ReservationRepository reservationRepository = new ReservationRepositoryMemory();

    @Override
    protected ReservationRepository getRepository() {
        return reservationRepository;
    }

    @Test
    public void store_a_copy_of_reservation_rather_than_reference() {
        Reservation clientSideReservation = new TestReservation("id-abcd");
        getRepository().add(clientSideReservation);

        assertThat(getRepository().getById(clientSideReservation.getId()),
                is(not(sameInstance(clientSideReservation))));
    }
}
