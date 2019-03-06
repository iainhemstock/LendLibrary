package com.iainhemstock.lendlibrary.infrastructure.persistence.memory;

import com.iainhemstock.lendlibrary.domain.model.reservation.ReservationRepository;
import com.iainhemstock.lendlibrary.infrastructure.persistence.ReservationRepositoryShould;

public final class ReservationRepositoryMemoryShould extends ReservationRepositoryShould {
    private ReservationRepository reservationRepository = new ReservationRepositoryMemory();

    @Override
    protected ReservationRepository getRepository() {
        return reservationRepository;
    }

    // todo: write a test ensures the reservation repo stores a copy of reservation and not a reference.
}
