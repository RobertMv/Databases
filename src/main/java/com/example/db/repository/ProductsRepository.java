package com.example.db.repository;

import com.example.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    void deleteAll();

    void deleteById(Long id);
}
