package com.example.db.repository;

import com.example.db.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findDishByName(String name);

    List<Dish> findAll();
}
