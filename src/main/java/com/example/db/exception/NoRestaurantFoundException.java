package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoRestaurantFoundException extends ResponseStatusException {
    public NoRestaurantFoundException() {
        super(HttpStatus.NOT_FOUND, "Ресторан не найден!");
    }
}
