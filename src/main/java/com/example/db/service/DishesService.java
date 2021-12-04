package com.example.db.service;

import com.example.db.dto.DishDto;
import com.example.db.entity.Dish;
import com.example.db.entity.Product;
import com.example.db.exception.NoDishFoundException;

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
