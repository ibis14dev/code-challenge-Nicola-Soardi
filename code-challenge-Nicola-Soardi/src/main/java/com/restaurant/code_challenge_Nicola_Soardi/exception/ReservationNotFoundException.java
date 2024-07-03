package com.restaurant.code_challenge_Nicola_Soardi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("The reservation id '" + id + "' does not exist in our records");
    }
}
