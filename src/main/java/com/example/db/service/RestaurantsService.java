package com.example.db.service;

import com.example.db.entity.Restaurant;

import java.util.List;
import java.util.Optional;

interface RestaurantsService {
    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(Long id);

    Optional<Restaurant> getRestaurant(String city);
}
