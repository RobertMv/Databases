package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "restaurants")
@Entity
@Data
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private Long id;

    private String address;

    private String City;

    private Double monthProfit;

    private Double yearProfit;

    @OneToMany
    private List<Employee> employees;

//    @ElementCollection
//    @CollectionTable(name = "product_weight_mapping", joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "product_name")
//    @Column(name = "weight")
//    private Map<Product, Double> leftProducts;

    @OneToMany
    private List<Dish> menu;
}
