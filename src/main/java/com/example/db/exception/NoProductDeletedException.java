package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoProductDeletedException extends ResponseStatusException {
    public NoProductDeletedException() {
        super(HttpStatus.BAD_REQUEST, "Продукт не может быть удален!\nПродукт с таким id должен существовать и не должен входить в состав блюд!");
    }
}
