package com.example.db.converter;

import com.example.db.dto.DishDto;
import com.example.db.dto.EmployeeDto;
import com.example.db.dto.ProductDto;
import com.example.db.entity.Dish;
import com.example.db.entity.Employee;
import com.example.db.entity.Product;
import com.example.db.repository.EmployeesRepository;
import com.example.db.repository.PositionsRepository;
import com.example.db.service.ProductsService;
import com.example.db.service.impl.DishesServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoEntityMapping {

    private final DishesServiceImpl dishesService;
    private final ProductsService productsService;
    private final EmployeesRepository employeesRepository;
    private final PositionsRepository positionsRepository;

    public DtoEntityMapping(DishesServiceImpl dishesService, ProductsService productsService, EmployeesRepository employeesRepository, PositionsRepository positionsRepository) {
        this.dishesService = dishesService;
        this.productsService = productsService;
        this.employeesRepository = employeesRepository;
        this.positionsRepository = positionsRepository;
    }

    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setAbout(productDto.getAbout());
        return product;
    }

    public ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productDto.getId());
        productDto.setName(productDto.getName());
        productDto.setAbout(productDto.getAbout());
        return productDto;
    }

    public Dish convert(DishDto dishDto){
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setAbout(dishDto.getAbout());
        dish.setId(dishDto.getId());
        dish.setSeasonal(dishDto.isSeasonal());
        dish.setRequiredProducts(getProductsByName(dishDto.getRequiredProducts()));
        return dish;
    }

    private List<Product> getProductsByName(List<String> names){
        List<Product> products = new ArrayList<>();
        for (String name : names) {
            products.add(productsService.getProductByName(name));
        }
        return products;
    }

    public DishDto convert (Dish dish){
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setAbout(dish.getAbout());
        dishDto.setSeasonal(dish.isSeasonal());
        dishDto.setRequiredProducts(getProducts(dish));
        return dishDto;
    }

    private List<String> getProducts (Dish dish){
        List<String> products = new ArrayList<>();
        List<Product> entities = dish.getRequiredProducts();
        for(Product product : entities){
            products.add(product.getName());
        }
        return products;
    }

    public Employee convert(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setSex(employeeDto.getSex());
        employee.setPassport(employeeDto.getPassport());
        employee.setEmploymentDate(employeeDto.getEmploymentDate());
        employee.setPhone(employeeDto.getPhone());
        employee.setPosition(positionsRepository.findByName(employeeDto.getPosition()).orElseThrow());
        employee.setHours(employeeDto.getHours());
        return employee;
    }

    public EmployeeDto convert (Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setPatronymic(employee.getPatronymic());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setSex(employee.getSex());
        employeeDto.setPassport(employee.getPassport());
        employeeDto.setEmploymentDate(employee.getEmploymentDate());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setPosition(employee.getPosition().getName());
        employeeDto.setHours(employee.getHours());
        return employeeDto;
    }
}