package com.example.db.controller;

import com.example.db.entity.Dish;
import com.example.db.service.impl.DishesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishesController {

    private final DishesServiceImpl dishesService;

    public DishesController(DishesServiceImpl dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("/get")
    public List<Dish> getDishes() {
        return dishesService.getAll();
    }

    @GetMapping("/get/{name}")
    public Dish getDish(@PathVariable String name) {
        return dishesService.getByName(name);
    }

    @GetMapping("/get-seasonal")
    public List<Dish> getSeasonalDishes() {
        return dishesService.getSeasonalDishes();
    }

    @PostMapping("/save")
    public void saveDish(@RequestBody Dish d) {
        dishesService.saveDish(d);
    }

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        dishesService.deleteAllDishes();
    }

    @DeleteMapping("/delete-seasonal")
    public void deleteSeasonalDishes() {
        dishesService.deleteSeasonal();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        dishesService.deleteById(id);
    }
}
