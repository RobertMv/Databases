package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoEmployeeDeletedException extends ResponseStatusException {
    public NoEmployeeDeletedException() {
        super(HttpStatus.BAD_REQUEST, "Работник не может быть удален!");
    }
}
