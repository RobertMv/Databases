package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoDishFoundException extends ResponseStatusException {
    public NoDishFoundException() {
        super(HttpStatus.NOT_FOUND, "Блюдо не найдено!");
    }
}

