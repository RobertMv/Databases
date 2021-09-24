package com.example.db.repository;

import com.example.db.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Optional<Restaurant> findByCity(String city);

    Optional<Restaurant> findTop1ByOrderByMonthProfit();

    Optional<Restaurant> findTop1ByOrderByYearProfit();

    void deleteAll();

    void deleteById(Long id);
}
