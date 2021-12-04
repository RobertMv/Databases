package com.example.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

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
    private Date birthDate;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private Long passport;

    @Column(nullable = false)
    private Date employmentDate;

    @Column(unique = true)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
