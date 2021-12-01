package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoPositionFoundException extends ResponseStatusException {
    public NoPositionFoundException() {
        super(HttpStatus.NOT_FOUND, "Позиция не найдена!");
    }
}
