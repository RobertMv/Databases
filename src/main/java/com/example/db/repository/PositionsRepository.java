package com.example.db.repository;

import com.example.db.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionsRepository extends JpaRepository<Position, Long> {

    List<Position> findAll();

    Optional<Position> findById(Long id);

    Optional<Position> findByName(String name);

    void deleteAll();

    void deleteById(Long id);
}
