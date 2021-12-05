package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoProductFoundException extends ResponseStatusException {
    public NoProductFoundException() {
        super(HttpStatus.NOT_FOUND, "Продукт не найден!");
    }
}
