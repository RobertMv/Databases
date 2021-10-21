package com.example.db.converter;

import com.example.db.dto.ProductDto;
import com.example.db.entity.Product;

public abstract class DtoEntityMapping {

    public static Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setAbout(productDto.getAbout());
        return product;
    }

    public static ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productDto.getId());
        productDto.setName(productDto.getName());
        productDto.setAbout(productDto.getAbout());
        return productDto;
    }
}
