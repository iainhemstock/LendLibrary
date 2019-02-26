package com.iainhemstock.lendlibrary.application.reserving;

import com.iainhemstock.lendlibrary.application.reserving.dto.ReservationDTO;

import java.util.List;

public interface ReservingService {

    String reserveBookForMember(String bookId, String memberId);

    void removeBookReservation(String reservationId);

    List<ReservationDTO> fetchReservationsByMember(String memberId);

    List<ReservationDTO> fetchReservationsByBook(String bookId);
}
