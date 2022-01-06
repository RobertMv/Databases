package com.example.db.service.food.impl;

import com.example.db.entity.food.Dish;
import com.example.db.exception.*;
import com.example.db.repository.food.DishesRepository;
import com.example.db.repository.food.ProductsRepository;
import com.example.db.service.food.DishesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishesServiceImpl implements DishesService {

    private final DishesRepository dishesRepository;
    private final ProductsRepository productsRepository;

    public DishesServiceImpl(DishesRepository dishesRepository, ProductsRepository productsRepository) {
        this.dishesRepository = dishesRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Dish> getAll() {
        try {
            return dishesRepository.findAll();
        } catch (Exception e) {
            throw new DishesEmptyException();
        }
    }

    public Dish getByName(String name) {
        return dishesRepository.findDishByName(name)
                .orElseThrow(NoDishFoundException::new);
    }

    @Override
    public List<Dish> getSeasonalDishes() {
        try {
            return dishesRepository.findDishesBySeasonalTrue();
        } catch (Exception e) {
            throw new DishesEmptyException();
        }
    }

    @Override
    public void saveDish(Dish dish) {
        try {
            dishesRepository.save(dish);
        } catch (Exception e) {
            throw new WrongInputException();
        }
    }

    @Override
    public void deleteAllDishes() {
        try {
            dishesRepository.deleteAll();
        } catch (Exception e) {
            throw new DishesEmptyException();
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            dishesRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoDishDeletedException();
        }
    }

    @Override
    public void deleteSeasonal() {
        try {
            dishesRepository.deleteDishesBySeasonalIsTrue();
        } catch (Exception e) {
            throw new DishesEmptyException();
        }
    }

    @Override
    public Dish getById(Long id) {
        return dishesRepository.findDishById(id)
                .orElseThrow(NoDishFoundException::new);
    }

    @Override
    public List<Dish> getDishByProduct(String productName) {
        try {
            return dishesRepository.findDishByRequiredProductsIsContaining(
                    productsRepository.findByName(productName)
                            .orElseThrow(NoProductFoundException::new));
        } catch (Exception e) {
            throw new NoDishFoundException();
        }
    }
}
