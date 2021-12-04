package com.example.db.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DishDto {

    private Long id;

    private String name;

    private Integer price;

    private String about;

    private boolean seasonal;

    private Set<String> requiredProducts;
}
