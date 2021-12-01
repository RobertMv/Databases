package com.example.db.service.impl;


import com.example.db.entity.Restaurant;
import com.example.db.exception.NoRestaurantDeletedException;
import com.example.db.exception.NoRestaurantFoundException;
import com.example.db.exception.RestaurantsEmptyException;
import com.example.db.exception.WrongInputException;
import com.example.db.repository.RestaurantsRepository;
import com.example.db.service.RestaurantsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantsServiceImpl implements RestaurantsService {

    private final RestaurantsRepository restaurantsRepository;

    public RestaurantsServiceImpl(RestaurantsRepository restaurantsRepository) {
        this.restaurantsRepository = restaurantsRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        try {
            return restaurantsRepository.findAll();
        } catch (Exception e) {
            throw new RestaurantsEmptyException();
        }
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantsRepository.findById(id)
                .orElseThrow(NoRestaurantFoundException::new);
    }


    @Override
    public Restaurant getRestaurantWithMaxMonthProfit() {
        return restaurantsRepository.findTop1ByOrderByMonthProfit()
                .orElseThrow(RestaurantsEmptyException::new);
    }

    @Override
    public Restaurant getRestaurantWithMaxYearProfit() {
        return restaurantsRepository.findTop1ByOrderByYearProfit()
                .orElseThrow(RestaurantsEmptyException::new);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        try {
            restaurantsRepository.save(restaurant);
        } catch (Exception e) {
            throw new WrongInputException();
        }
    }

    @Override
    public void deleteAll() {
        try {
            restaurantsRepository.deleteAll();
        } catch (Exception e) {
            throw new RestaurantsEmptyException();
        }
    }

    @Override
    public void deleteRestaurantById(Long id) {
        try {
            restaurantsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoRestaurantDeletedException();
        }
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantsRepository.findByName(name)
                .orElseThrow(NoRestaurantFoundException::new);
    }
}
