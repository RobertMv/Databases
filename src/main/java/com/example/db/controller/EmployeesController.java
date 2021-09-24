package com.example.db.controller;

import com.example.db.entity.Employee;
import com.example.db.service.impl.EmployeesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesServiceImpl employeesService;

    public EmployeesController(EmployeesServiceImpl employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/get")
    public List<Employee> getEmployees() {
        return employeesService.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeesService.getEmployee(id);
    }

    @GetMapping("/get/{name}-{surname}-{patronymic}")
    public Employee getEmployee(@PathVariable String name, @PathVariable String surname, @PathVariable String patronymic) {
        return employeesService.getEmployee(name, surname, patronymic);
    }

    @GetMapping("/get/passport/{passport}")
    public Employee getEmployeeByPassport(@PathVariable Long passport) {
        return employeesService.getEmployee(passport);
    }

    @PostMapping("/save")
    public void saveEmployee(Employee employee) {
        employeesService.saveEmployee(employee);
    }

    @DeleteMapping("/delete-all")
    public void deleteEmployees() {
        employeesService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeesService.deleteEmployeeById(id);
    }
}
