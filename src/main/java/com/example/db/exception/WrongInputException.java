package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongInputException extends ResponseStatusException {
    public WrongInputException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Введены неверные данные, пожалуйста проверьте!");
    }
}
