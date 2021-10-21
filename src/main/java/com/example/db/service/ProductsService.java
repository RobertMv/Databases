package com.example.db.service;

import com.example.db.entity.Product;

import java.util.List;

public interface ProductsService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void saveProduct(Product product);

    void deleteAll();

    void deleteProductById(Long id);

    Product getProductByName(String name);
}
