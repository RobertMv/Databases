package com.example.db.service.impl;

import com.example.db.entity.Product;
import com.example.db.exception.NoProductDeletedException;
import com.example.db.exception.NoProductFoundException;
import com.example.db.exception.ProductsEmptyException;
import com.example.db.exception.WrongInputException;
import com.example.db.repository.ProductsRepository;
import com.example.db.service.ProductsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productsRepository.findAll();
        } catch (Exception e) {
            throw new ProductsEmptyException();
        }
    }

    @Override
    public Product getProductById(Long id) {
        return productsRepository.findById(id)
                .orElseThrow(NoProductFoundException::new);
    }

    @Override
    public void saveProduct(Product product) {
        try {
            productsRepository.save(product);
        } catch (Exception e) {
            throw new WrongInputException();
        }
    }

    @Override
    public void deleteAll() {
        try {
            productsRepository.deleteAll();
        } catch (Exception e) {
            throw new ProductsEmptyException();
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            productsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NoProductDeletedException();
        }
    }

    @Override
    public Product getProductByName(String name) {
        return productsRepository.findByName(name)
                .orElseThrow(NoProductFoundException::new);
    }
}
