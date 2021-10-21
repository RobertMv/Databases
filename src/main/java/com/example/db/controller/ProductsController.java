package com.example.db.controller;

import com.example.db.converter.DtoEntityMapping;
import com.example.db.dto.ProductDto;
import com.example.db.entity.Product;
import com.example.db.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable Long id) {
        return productsService.getProductById(id);
    }

    @GetMapping("/name/{name}")
    public Product getByName(@PathVariable String name) {
        return productsService.getProductByName(name);
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductDto productDto) {
        productsService.saveProduct(DtoEntityMapping.convert(productDto));
    }

    @DeleteMapping("/delete-all")
    public void deleteAllProducts() {
        productsService.deleteAll();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteProductById(id);
    }

}
