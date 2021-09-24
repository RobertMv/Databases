package com.example.db.service.impl;

import com.example.db.entity.Dish;
import com.example.db.repository.DishesRepository;
import com.example.db.service.DishesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishesServiceImpl implements DishesService {

    private final DishesRepository dishesRepository;

    public DishesServiceImpl(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @Override
    public List<Dish> getAll() {
        return dishesRepository.findAll();
    }

    public Dish getByName(String name) {
        Optional<Dish> d = dishesRepository.findDishByName(name);
        return d.orElseGet(d::orElseThrow);
    }

    @Override
    public List<Dish> getSeasonalDishes() {
        return dishesRepository.findDishesBySeasonalIsTrue();
    }

    @Override
    public void saveDish(Dish d) {
        dishesRepository.save(d);
    }

    @Override
    public void deleteAllDishes() {
        dishesRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        dishesRepository.deleteById(id);
    }

    @Override
    public void deleteSeasonal() {
        dishesRepository.deleteDishesBySeasonalIsTrue();
    }
}
