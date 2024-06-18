package com.microservices.product.service;

import com.microservices.product.entities.Product;

import java.util.List;

public interface IProductService {

    List<Product> finAll();

    List<Product> findByName(String name);

    void save(Product product);

    void update(Product product);

    Product updateProductIngredients(Long productId, List<Long> ingredientIds, List<Double> quantities);



}
