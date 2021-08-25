package com.example.db.controller;

import com.example.db.entity.Restaurant;
import com.example.db.service.RestaurantsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantsController {

    private final RestaurantsServiceImpl restaurantsService;

    public RestaurantsController(RestaurantsServiceImpl restaurantsService) {
        this.restaurantsService = restaurantsService;
    }

    @GetMapping("/get")
    public List<Restaurant> getRestaurants() {
        return restaurantsService.getAllRestaurants();
    }

    @GetMapping("/get/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantsService.getRestaurant(id);
    }

    @GetMapping("/get/{city}")
    public Restaurant getRestaurant(@PathVariable String city) {
        Optional<Restaurant> restaurant = restaurantsService.getRestaurant(city);
        return restaurant.orElseGet(restaurant::orElseThrow);
    }
}
