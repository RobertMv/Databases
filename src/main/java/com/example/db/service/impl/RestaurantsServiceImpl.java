package com.example.db.service.impl;


import com.example.db.entity.Restaurant;
import com.example.db.repository.RestaurantsRepository;
import com.example.db.service.RestaurantsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantsServiceImpl implements RestaurantsService {

    private final RestaurantsRepository restaurantsRepository;

    public RestaurantsServiceImpl(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantsRepository.findAll();
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantsRepository.findById(id);
        return restaurant.orElseGet(restaurant::orElseThrow);
    }

    @Override
    public Restaurant getRestaurant(String city) {
        Optional<Restaurant> restaurant = restaurantsRepository.findByCity(city);
        return restaurant.orElseGet(restaurant::orElseThrow);
    }

    @Override
    public Restaurant getRestaurantWithMaxMonthProfit() {
        Optional<Restaurant> restaurant = restaurantsRepository.findTop1ByOrderByMonthProfit();
        return restaurant.orElseGet(restaurant::orElseThrow);
    }

    @Override
    public Restaurant getRestaurantWithMaxYearProfit() {
        Optional<Restaurant> restaurant = restaurantsRepository.findTop1ByOrderByYearProfit();
        return restaurant.orElseGet(restaurant::orElseThrow);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantsRepository.save(restaurant);
    }

    @Override
    public void deleteAll() {
        restaurantsRepository.deleteAll();
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantsRepository.deleteById(id);
    }
}
