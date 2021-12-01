package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoPositionDeletedException extends ResponseStatusException {
    public NoPositionDeletedException() {
        super(HttpStatus.BAD_REQUEST, "Позиция не может быть удалена!");
    }
}
