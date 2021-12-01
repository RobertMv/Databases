package com.example.db.dto;

import lombok.Data;

import java.util.List;

@Data
public class DishDto {

    private Long id;

    private String name;

    private String about;

    private boolean seasonal;

    private List<String> requiredProducts;

    private Integer price;
}
