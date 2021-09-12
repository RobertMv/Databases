package com.example.db.service.impl;

import com.example.db.entity.Employee;
import com.example.db.repository.EmployeesRepository;
import com.example.db.service.EmployeesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> e = employeesRepository.findById(id);
        return e.orElseGet(e::orElseThrow);
    }

    @Override
    public Employee getEmployeeByPassport(Long passport) {
        Optional<Employee> e = employeesRepository.findByPassport(passport);
        return e.orElseGet(e::orElseThrow);
    }

    @Override
    public Employee getEmployee(String name, String surname, String patronymic) {
        Optional<Employee> e = employeesRepository.findByNameAndSurnameAndPatronymic(name, surname, patronymic);
        return e.orElseGet(e::orElseThrow);
    }

    @Override
    public void saveEmployee(Employee e) {
        employeesRepository.saveEmployee(e);
    }

    @Override
    public void deleteAll() {
        employeesRepository.deleteAll();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeesRepository.deleteEmployeeById(id);
    }
}
