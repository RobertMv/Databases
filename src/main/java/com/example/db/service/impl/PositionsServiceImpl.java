package com.example.db.service.impl;

import com.example.db.entity.Position;
import com.example.db.exception.NoPositionDeletedException;
import com.example.db.exception.NoPositionFoundException;
import com.example.db.exception.PositionsEmptyException;
import com.example.db.exception.WrongInputException;
import com.example.db.repository.PositionsRepository;
import com.example.db.service.PositionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsServiceImpl implements PositionsService {
    private final PositionsRepository positionsRepository;

    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public List<Position> getAllPositions() {
        try {
            return positionsRepository.findAll();
        } catch (Exception e) {
            throw new PositionsEmptyException();
        }
    }

    @Override
    public Position getPositionById(Long id) {
        return positionsRepository.findById(id)
                .orElseThrow(NoPositionFoundException::new);
    }

    @Override
    public void savePosition(Position position) {
        try {
            positionsRepository.save(position);
        } catch (Exception e) {
            throw new WrongInputException();
        }
    }

    @Override
    public void deleteAll() {
        try {
            positionsRepository.deleteAll();
        } catch (Exception e) {
            throw new PositionsEmptyException();
        }
    }

    @Override
    public void deletePositionById(Long id) {
        try {
            positionsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoPositionDeletedException();
        }
    }

    @Override
    public Position getPositionByName(String name) {
        return positionsRepository.findByName(name)
                .orElseThrow(NoPositionFoundException::new);
    }
}
