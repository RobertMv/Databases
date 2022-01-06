package com.example.db.converter;

import com.example.db.dto.DishDto;
import com.example.db.dto.EmployeeDto;
import com.example.db.dto.ProductDto;
import com.example.db.dto.RestaurantDto;
import com.example.db.entity.food.Dish;
import com.example.db.entity.food.Product;
import com.example.db.entity.staff.Employee;
import com.example.db.entity.staff.Restaurant;
import com.example.db.repository.staff.PositionsRepository;
import com.example.db.service.food.DishesService;
import com.example.db.service.food.ProductsService;
import com.example.db.service.staff.EmployeesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoEntityMapping {

    private final ProductsService productsService;
    private final PositionsRepository positionsRepository;
    private final DishesService dishesService;
    private final EmployeesService employeesService;

    public DtoEntityMapping(ProductsService productsService, PositionsRepository positionsRepository, DishesService dishesService, EmployeesService employeesService) {
        this.productsService = productsService;
        this.positionsRepository = positionsRepository;
        this.dishesService = dishesService;
        this.employeesService = employeesService;
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

    public Dish convert(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setAbout(dishDto.getAbout());
        dish.setId(dishDto.getId());
        dish.setSeasonal(dishDto.isSeasonal());
        dish.setRequiredProducts(getProductsByName(dishDto.getRequiredProducts()));
        dish.setPrice(dishDto.getPrice());
        return dish;
    }

    private Set<Product> getProductsByName(Set<String> names) {
        return names
                .stream()
                .map(productsService::getProductByName)
                .collect(Collectors.toSet());
    }

    public DishDto convert(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setId(dish.getId());
        dishDto.setName(dish.getName());
        dishDto.setAbout(dish.getAbout());
        dishDto.setSeasonal(dish.isSeasonal());
        dishDto.setRequiredProducts(getProducts(dish));
        dishDto.setPrice(dish.getPrice());
        return dishDto;
    }

    private Set<String> getProducts(Dish dish) {
        return dish.getRequiredProducts()
                .stream()
                .map(Product::getName)
                .collect(Collectors.toSet());
    }

    public Employee convert(EmployeeDto employeeDto) {
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
        return employee;
    }

    public EmployeeDto convert(Employee employee) {
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
        return employeeDto;
    }

    public Restaurant convert(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setMonthProfit(restaurantDto.getMonthProfit());
        restaurant.setYearProfit(restaurantDto.getYearProfit());
        restaurant.setEmployees(getRestaurantEmployees(restaurantDto));
        return restaurant;
    }

    private List<Employee> getRestaurantEmployees(RestaurantDto restaurantDto) {
        return restaurantDto.getEmployees()
                .stream()
                .map(dto -> employeesService.getEmployee(Long.parseLong(dto.get(0))))
                .collect(Collectors.toList());
    }

    public RestaurantDto convert(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setMonthProfit(restaurant.getMonthProfit());
        restaurantDto.setYearProfit(restaurant.getYearProfit());
        restaurantDto.setEmployees(getRestaurantDtoEmployees(restaurant));
        return restaurantDto;
    }

    private List<List<String>> getRestaurantDtoEmployees(Restaurant restaurant) {
        return restaurant.getEmployees()
                .stream()
                .map(employee -> List.of(
                        String.valueOf(employee.getId()),
                        employee.getPosition().getName(),
                        employee.getName(),
                        employee.getSurname()))
                .collect(Collectors.toList());
    }
}