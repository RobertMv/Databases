package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoRestaurantDeletedException extends ResponseStatusException {
    public NoRestaurantDeletedException() {
        super(HttpStatus.BAD_REQUEST, "Ресторан не может быть удален!");
    }
}
