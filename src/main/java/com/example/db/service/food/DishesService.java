package com.example.db.service.food;

import com.example.db.entity.food.Dish;

import java.util.List;

public interface DishesService {
    List<Dish> getAll();

    Dish getByName(String name);

    List<Dish> getSeasonalDishes();

    void saveDish(Dish d);

    void deleteAllDishes();

    void deleteById(Long id);

    void deleteSeasonal();

    Dish getById(Long id);

    List<Dish> getDishByProduct(String productName);
}
