package com.microservices.product.persistence;

import com.microservices.product.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String productName);

}
