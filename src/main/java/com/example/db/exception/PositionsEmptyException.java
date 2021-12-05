package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PositionsEmptyException extends ResponseStatusException {
    public PositionsEmptyException() {
        super(HttpStatus.NOT_FOUND, "Позиций нет!");
    }
}
