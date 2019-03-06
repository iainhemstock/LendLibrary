package com.iainhemstock.lendlibrary.application.reserving.impls.assembler;

import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;
import com.iainhemstock.lendlibrary.domain.model.reservation.Reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class ReservationDTOAssembler {

    public List<ReservationDTO> toDTOList(List<Reservation> reservations) {
        requireNonNull(reservations, "List of reservations is required");

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDTOS.add(new ReservationDTO(
                    reservation.getId().toString(),
                    reservation.getBookId().toString(),
                    reservation.getMemberId().toString(),
                    reservation.getReservationDate()));
        }
        return reservationDTOS;
    }
}
