package com.restaurant.code_challenge_Nicola_Soardi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.BAD_REQUEST)
public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Long id) {
        super("The user id '" + id + "' does not exist in our records");
    }
}
