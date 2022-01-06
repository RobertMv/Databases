package com.example.db.service.staff;

import com.example.db.entity.staff.Employee;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAllEmployees();

    Employee getEmployee(Long id);

    Employee getEmployeeByPassport(Long passport);

    Employee getEmployeeFIO(String name, String surname, String patronymic);

    void saveEmployee(Employee employee);

    void deleteAll();

    void deleteEmployeeById(Long id);
}
