package com.example.db.repository;

import com.example.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Optional<Employee> findByPassport(Long passport);

    Optional<Employee> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

    void deleteAll();

    void deleteById(Long id);
}
