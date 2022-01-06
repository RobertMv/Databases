package com.example.db.repository.food;

import com.example.db.entity.food.Dish;
import com.example.db.entity.food.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dish, Long> {

    Optional<Dish> findDishByName(String name);

    Optional<Dish> findById(Long id);

    List<Dish> findAll();

    List<Dish> findDishesBySeasonalTrue();

    List<Dish> findDishByRequiredProductsIsContaining(Product product);

    void deleteAll();

    void deleteById(Long id);

    void deleteDishesBySeasonalIsTrue();

    Optional<Dish> findDishById(Long id);
}
