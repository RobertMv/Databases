package com.example.db.repository;

import com.example.db.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findDishByName(String name);

    List<Dish> findAll();
}
