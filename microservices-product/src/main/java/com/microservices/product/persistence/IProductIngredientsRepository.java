package com.microservices.product.persistence;

import com.microservices.product.entities.ProductIngredients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductIngredientsRepository extends CrudRepository<ProductIngredients, Long> {

    List<ProductIngredients> findByProductId(Long productId);

}
