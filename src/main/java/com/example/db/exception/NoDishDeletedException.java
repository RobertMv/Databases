package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoDishDeletedException extends ResponseStatusException {
    public NoDishDeletedException() {
        super(HttpStatus.BAD_REQUEST, "Блюдо не может быть удалено!");
    }
}
