package com.example.db.service.impl;

import com.example.db.entity.Dish;
import com.example.db.service.DishesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {
    @Override
    public List<Dish> getAll() {
        return null;
    }

    public Dish getByName(String id) {
        return null;
    }

    @Override
    public List<Dish> getSeasonalDishes() {
        return null;
    }

    @Override
    public void saveDish() {

    }

    @Override
    public void deleteAllDishes() {

    }

    @Override
    public void deleteById(Long id) {

    }
}
