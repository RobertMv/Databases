package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private Long passport;

    @Column(nullable = false)
    private Date dateOfEmployment;

    @Column(unique = true)
    private String phone;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private Integer hours;
}
