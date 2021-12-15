package com.example.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductsEmptyException extends ResponseStatusException {
    public ProductsEmptyException() {
        super(HttpStatus.NOT_FOUND, "Продуктов уже нет или из них состоят блюда!");
    }
}
