package com.example.db.converter;

import com.example.db.dto.DishDto;
import com.example.db.dto.ProductDto;
import com.example.db.entity.Dish;
import com.example.db.entity.Product;
import com.example.db.repository.DishesRepository;
import com.example.db.service.ProductsService;
import com.example.db.service.impl.DishesServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoEntityMapping {

    private final DishesServiceImpl dishesService;
    private final ProductsService productsService;

    public DtoEntityMapping(DishesServiceImpl dishesService, ProductsService productsService) {
        this.dishesService = dishesService;
        this.productsService = productsService;
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
}
