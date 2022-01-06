package com.example.db.controller;

import com.example.db.converter.DtoEntityMapping;
import com.example.db.dto.EmployeeDto;
import com.example.db.service.staff.impl.EmployeesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesServiceImpl employeesService;
    private final DtoEntityMapping dtoEntityMapping;

    public EmployeesController(EmployeesServiceImpl employeesService, DtoEntityMapping dtoEntityMapping) {
        this.employeesService = employeesService;
        this.dtoEntityMapping = dtoEntityMapping;
    }

    @GetMapping("/all")
    public List<EmployeeDto> getEmployees() {
        return employeesService.getAllEmployees().stream()
                .map(dtoEntityMapping::convert)
                .collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    public EmployeeDto getEmployee(@PathVariable Long id) {
        return dtoEntityMapping.convert(employeesService.getEmployee(id));
    }

    @GetMapping("/fio/{name}-{surname}-{patronymic}")
    public EmployeeDto getEmployeeByFIO(@PathVariable String name, @PathVariable String surname, @PathVariable String patronymic) {
        return dtoEntityMapping.convert(employeesService.getEmployeeFIO(name, surname, patronymic));
    }

    @GetMapping("/passport/{passport}")
    public EmployeeDto getEmployeeByPassport(@PathVariable Long passport) {
        return dtoEntityMapping.convert(employeesService.getEmployeeByPassport(passport));
    }

    @PostMapping("/save")
    public void saveEmployee(@RequestBody EmployeeDto employeeDto) {
        employeesService.saveEmployee(dtoEntityMapping.convert(employeeDto));
    }

    @DeleteMapping("/delete-all")
    public void deleteEmployees() {
        employeesService.deleteAll();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeesService.deleteEmployeeById(id);
    }
}
