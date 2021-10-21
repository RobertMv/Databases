package com.example.db.service.impl;

import com.example.db.entity.Product;
import com.example.db.repository.ProductsRepository;
import com.example.db.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productsRepository.findById(id);
        return product.orElseGet(product::orElseThrow);
    }

    @Override
    public void saveProduct(Product product) {
        productsRepository.save(product);
    }

    @Override
    public void deleteAll() {
        productsRepository.deleteAll();
    }

    @Override
    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Product getProductByName(String name) {
        Optional<Product> product = productsRepository.findByName(name);
        return product.orElseGet(product::orElseThrow);
    }
}
