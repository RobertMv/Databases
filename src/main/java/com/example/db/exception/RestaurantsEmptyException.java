package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RestaurantsEmptyException extends ResponseStatusException {
    public RestaurantsEmptyException() {
        super(HttpStatus.NOT_FOUND, "Ресторанов уже нет!");
    }
}
