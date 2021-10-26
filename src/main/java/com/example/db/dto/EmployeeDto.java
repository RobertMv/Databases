package com.example.db.dto;

import com.example.db.entity.Position;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
public class EmployeeDto {
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthDate;

    private String sex;

    private Long passport;

    private LocalDate employmentDate;

    private String phone;

    private String position;

    private Integer hours;
}
