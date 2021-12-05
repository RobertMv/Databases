package com.example.db.dto;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantDto {

    private Long id;

    private String name;

    private String address;

    private Long monthProfit;

    private Long yearProfit;

    private List<List<String>> employees; //list of employees, each employee here is a list of id, name and surname
}
