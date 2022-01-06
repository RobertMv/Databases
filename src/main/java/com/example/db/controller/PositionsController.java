package com.example.db.controller;

import com.example.db.entity.staff.Position;
import com.example.db.service.staff.PositionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionsController {

    private final PositionsService positionsService;

    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    @GetMapping("/all")
    public List<Position> getAllProducts() {
        return positionsService.getAllPositions();
    }

    @GetMapping("/id/{id}")
    public Position getById(@PathVariable Long id) {
        return positionsService.getPositionById(id);
    }

    @GetMapping("/name/{name}")
    public Position getByName(@PathVariable String name) {
        return positionsService.getPositionByName(name);
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Position position) {
        positionsService.savePosition(position);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllProducts() {
        positionsService.deleteAll();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable Long id) {
        positionsService.deletePositionById(id);
    }
}
