package com.example.db.service;

import com.example.db.entity.Dish;

import java.util.List;

public interface DishesService {
    List<Dish> getAll();

    Dish getByName(String name);

    List<Dish> getSeasonalDishes();

    void saveDish();

    void deleteAllDishes();

    void deleteById(Long id);
}
