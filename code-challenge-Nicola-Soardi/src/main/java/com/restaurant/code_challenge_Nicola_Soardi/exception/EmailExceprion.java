package com.restaurant.code_challenge_Nicola_Soardi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class EmailExceprion extends RuntimeException {

    public EmailExceprion(String email) {
        super("email '" + email + "' already exists in our records. Us a different email.");
    }
}
