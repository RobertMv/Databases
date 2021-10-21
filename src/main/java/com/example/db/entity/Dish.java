package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id")
    private Long id;

    private boolean seasonal;

    @OneToMany
    private List<Product> requiredProducts;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false, unique = true)
    private String name;
}
