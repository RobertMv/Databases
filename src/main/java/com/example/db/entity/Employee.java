package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String patronymic;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private Long passport;

    @Column(nullable = false)
    private LocalDate employmentDate;

    @Column(nullable = false)
    private LocalDate date;

    @Column(unique = true)
    private String phone;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private Integer hours;
}
