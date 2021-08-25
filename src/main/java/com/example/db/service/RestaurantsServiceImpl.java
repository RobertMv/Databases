package com.example.db.service;


import com.example.db.entity.Restaurant;
import com.example.db.repository.RestaurantsRepository;
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
    public Optional<Restaurant> getRestaurant(String city) {
        return restaurantsRepository.findByCity(city);
    }


}
