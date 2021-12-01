package com.example.db.service.impl;

import com.example.db.entity.Employee;
import com.example.db.exception.EmployeesEmptyException;
import com.example.db.exception.NoEmployeeDeletedException;
import com.example.db.exception.NoEmployeeFoundException;
import com.example.db.exception.WrongInputException;
import com.example.db.repository.EmployeesRepository;
import com.example.db.service.EmployeesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            return employeesRepository.findAll();
        } catch (Exception e) {
            throw new EmployeesEmptyException();
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeesRepository.findById(id)
                .orElseThrow(NoEmployeeFoundException::new);
    }

    @Override
    public Employee getEmployeeByPassport(Long passport) {
        return employeesRepository.findByPassport(passport)
                .orElseThrow(NoEmployeeFoundException::new);
    }

    @Override
    public Employee getEmployeeFIO(String name, String surname, String patronymic) {
        return employeesRepository.findByNameAndSurnameAndPatronymic(name, surname, patronymic)
                .orElseThrow(NoEmployeeFoundException::new);
    }

    @Override
    public void saveEmployee(Employee employee) {
        try {
            employeesRepository.save(employee);
        } catch (Exception e) {
            throw new WrongInputException();
        }
    }

    @Override
    public void deleteAll() {
        try {
            employeesRepository.deleteAll();
        } catch (Exception e) {
            throw new EmployeesEmptyException();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try {
            employeesRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoEmployeeDeletedException();
        }
    }
}
