package com.example.db.controller;

import com.example.db.converter.DtoEntityMapping;
import com.example.db.dto.DishDto;
import com.example.db.entity.Dish;
import com.example.db.repository.DishesRepository;
import com.example.db.service.impl.DishesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishesController {

    private final DishesServiceImpl dishesService;
    private final DtoEntityMapping dtoEntityMapping;
    private final DishesRepository dishesRepository;

    public DishesController(DishesServiceImpl dishesService, DtoEntityMapping dtoEntityMapping, DishesRepository dishesRepository) {
        this.dishesService = dishesService;
        this.dtoEntityMapping = dtoEntityMapping;
        this.dishesRepository = dishesRepository;
    }

    @GetMapping("/all")
    public List<DishDto> getDishes() {
        List<Dish> dishes = dishesService.getAll();
        List<DishDto> dtos = new ArrayList<>();
        for (Dish d : dishes) {
            dtos.add(dtoEntityMapping.convert(d));
        }
        return dtos;
    }

    @GetMapping("/name/{name}")
    public DishDto getDish(@PathVariable String name) {
        return dtoEntityMapping.convert(dishesService.getByName(name));
    }

    @GetMapping("/id/{id}")
    public DishDto getDishById(@PathVariable Long id) {
        return dtoEntityMapping.convert(dishesService.getById(id));
    }

    @GetMapping("/seasonal")
    public List<DishDto> getSeasonalDishes() {
        List<Dish> dishes = dishesService.getSeasonalDishes();
        List<DishDto> dtos = new ArrayList<>();
        for (Dish d : dishes) {
            dtos.add(dtoEntityMapping.convert(d));
        }
        return dtos;
    }

    @PostMapping("/save")
    public void saveDish(@RequestBody DishDto dishDto) {
        dishesService.saveDish(dtoEntityMapping.convert(dishDto));
    }

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        dishesService.deleteAllDishes();
    }

    @DeleteMapping("/delete-seasonal")
    public void deleteSeasonalDishes() {
        dishesService.deleteSeasonal();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable Long id) {
        dishesService.deleteById(id);
    }
}
