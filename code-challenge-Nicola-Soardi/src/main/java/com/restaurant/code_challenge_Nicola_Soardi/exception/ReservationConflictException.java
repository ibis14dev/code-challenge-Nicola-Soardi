package com.restaurant.code_challenge_Nicola_Soardi.exception;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeFormatter;

public class ReservationConflictException extends RuntimeException {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public ReservationConflictException(Reservation reservation) {
        super("You already have a reservation for this time slot, hours and date: "+reservation.getReservationDate().format(DATE_TIME_FORMATTER)+" .");
    }
}

