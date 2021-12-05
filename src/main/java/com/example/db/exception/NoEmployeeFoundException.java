package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoEmployeeFoundException extends ResponseStatusException {
    public NoEmployeeFoundException() {
        super(HttpStatus.NOT_ACCEPTABLE, "Работник не найден!");
    }
}
