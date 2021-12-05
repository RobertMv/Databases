package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DishesEmptyException extends ResponseStatusException {
    public DishesEmptyException() {
        super(HttpStatus.NOT_FOUND, "Меню пустое, блюд нет!");
    }
}
