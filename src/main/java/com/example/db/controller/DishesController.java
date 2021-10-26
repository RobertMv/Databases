package com.example.db.controller;

import com.example.db.converter.DtoEntityMapping;
import com.example.db.dto.DishDto;
import com.example.db.entity.Dish;
import com.example.db.repository.DishesRepository;
import com.example.db.service.impl.DishesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return dishesService.getAll().stream().map(dtoEntityMapping::convert).collect(Collectors.toList());
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
        return dishesService.getSeasonalDishes().stream().map(dtoEntityMapping::convert).collect(Collectors.toList());
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
