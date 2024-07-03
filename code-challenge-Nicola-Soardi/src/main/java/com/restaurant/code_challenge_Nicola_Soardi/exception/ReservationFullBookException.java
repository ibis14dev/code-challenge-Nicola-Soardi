package com.restaurant.code_challenge_Nicola_Soardi.exception;

import com.restaurant.code_challenge_Nicola_Soardi.entity.Reservation;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeFormatter;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class ReservationFullBookException extends RuntimeException{
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public ReservationFullBookException(Reservation reservation) {
        super("The restaurant is full for the date " + reservation.getReservationDate().format(DATE_TIME_FORMATTER) + " please choose another date.");
    }
}
