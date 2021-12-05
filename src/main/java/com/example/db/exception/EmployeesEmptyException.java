package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeesEmptyException extends ResponseStatusException {
    public EmployeesEmptyException() {
        super(HttpStatus.NOT_FOUND, "Работников нет!");
    }
}
