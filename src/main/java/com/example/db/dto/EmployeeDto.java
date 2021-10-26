package com.example.db.dto;

import com.example.db.entity.Position;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

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
