package com.iainhemstock.lendlibrary.domain.model.reservation;

public final class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String msg) {
        super(msg);
    }
}
