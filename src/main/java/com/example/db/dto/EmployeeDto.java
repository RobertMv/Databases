package com.example.db.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EmployeeDto {
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private Date birthDate;

    private String sex;

    private Long passport;

    private Date employmentDate;

    private String phone;

    private String position;

    private Integer hours;
}
