package com.example.db.service;

import com.example.db.entity.Restaurant;

import java.util.List;

public interface RestaurantsService {
    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(Long id);

    Restaurant getRestaurant(String city);

    Restaurant getRestaurantWithMaxMonthProfit();

    Restaurant getRestaurantWithMaxYearProfit();

    void saveRestaurant(Restaurant restaurant);

    void deleteAll();

    void deleteRestaurantById(Long id);
}
