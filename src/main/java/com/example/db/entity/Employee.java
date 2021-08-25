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

    private String surname;

    private String name;

    private String patronymic;

    private Date dateOfBirth;

    private String sex;

    private Long passport;

    private Date dateOfEmployment;

    private String phone;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private Integer hours;
}
