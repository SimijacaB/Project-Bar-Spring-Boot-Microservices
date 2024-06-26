package com.microservices.product.service;

import com.microservices.product.dto.ProductIngredientRequestDTO;
import com.microservices.product.entities.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    List<Product> findByName(String name);

    void save(Product product);

    void update(Product product);

    Product updateProductIngredients(Long productId, List<ProductIngredientRequestDTO> ingredients);



}
