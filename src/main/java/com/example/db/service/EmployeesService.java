package com.example.db.service;

import com.example.db.entity.Employee;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAllEmployees();

    Employee getEmployee(Long id);

    Employee getEmployeeByPassport(Long passport);

    Employee getEmployee(String name, String surname, String patronymic);

    void saveEmployee(Employee employee);

    void deleteAll();

    void deleteEmployeeById(Long id);
}
