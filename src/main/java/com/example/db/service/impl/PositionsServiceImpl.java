package com.example.db.service.impl;

import com.example.db.entity.Position;
import com.example.db.repository.PositionsRepository;
import com.example.db.service.PositionsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionsServiceImpl implements PositionsService {
    private final PositionsRepository positionsRepository;

    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionsRepository.findAll();
    }

    @Override
    public Position getPositionById(Long id) {
        Optional<Position> position = positionsRepository.findById(id);
        return position.orElseGet(position::orElseThrow);
    }

    @Override
    public void savePosition(Position position) {
        positionsRepository.save(position);
    }

    @Override
    public void deleteAll() {
        positionsRepository.deleteAll();
    }

    @Override
    public void deletePositionById(Long id) {
        positionsRepository.deleteById(id);
    }

    @Override
    public Position getPositionByName(String name) {
        Optional<Position> position = positionsRepository.findByName(name);
        return position.orElseGet(position::orElseThrow);
    }
}
