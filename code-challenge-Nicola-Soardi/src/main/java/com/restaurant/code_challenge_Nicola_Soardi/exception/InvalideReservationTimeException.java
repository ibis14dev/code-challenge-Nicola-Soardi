package com.restaurant.code_challenge_Nicola_Soardi.exception;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeFormatter;

public class InvalideReservationTimeException extends RuntimeException {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        public InvalideReservationTimeException(Reservation reservation) {
            super("Reservation time " + reservation.getReservationDate().format(DATE_TIME_FORMATTER) + " must be between 19:00 and 23:00");
        }

}
