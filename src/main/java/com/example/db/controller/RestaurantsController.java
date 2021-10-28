package com.example.db.controller;

import com.example.db.converter.DtoEntityMapping;
import com.example.db.dto.RestaurantDto;
import com.example.db.entity.Restaurant;
import com.example.db.service.impl.RestaurantsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {

    private final RestaurantsServiceImpl restaurantsService;
    private final DtoEntityMapping dtoEntityMapping;

    public RestaurantsController(RestaurantsServiceImpl restaurantsService, DtoEntityMapping dtoEntityMapping) {
        this.restaurantsService = restaurantsService;
        this.dtoEntityMapping = dtoEntityMapping;
    }

    @GetMapping("/all")
    public List<RestaurantDto> getRestaurants() {
        return restaurantsService.getAllRestaurants()
                .stream()
                .map(dtoEntityMapping::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public RestaurantDto getRestaurant(@PathVariable Long id) {
        return dtoEntityMapping.convert(restaurantsService.getRestaurant(id));
    }

    @GetMapping("/name/{name}")
    public RestaurantDto getRestaurantByName(@PathVariable String name) {
        return dtoEntityMapping.convert(restaurantsService.getRestaurantByName(name));
    }

    @GetMapping("/get/month-profit")
    public RestaurantDto getRestaurantWithMaxMonthProfit() {
        return dtoEntityMapping.convert(restaurantsService.getRestaurantWithMaxMonthProfit());
    }

    @GetMapping("/get/year-profit")
    public RestaurantDto getRestaurantWithMaxYearProfit() {
        return dtoEntityMapping.convert(restaurantsService.getRestaurantWithMaxYearProfit());
    }

    @PostMapping("/save")
    public void saveRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantsService.saveRestaurant(dtoEntityMapping.convert(restaurantDto));
    }

    @DeleteMapping("/delete-all")
    public void deleteRestaurants() {
        restaurantsService.deleteAll();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantsService.deleteRestaurantById(id);
    }
}
