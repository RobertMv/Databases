package com.example.db.service.staff;

import com.example.db.entity.staff.Position;

import java.util.List;

public interface PositionsService {
    List<Position> getAllPositions();

    Position getPositionById(Long id);

    void savePosition(Position position);

    void deleteAll();

    void deletePositionById(Long id);

    Position getPositionByName(String name);
}
