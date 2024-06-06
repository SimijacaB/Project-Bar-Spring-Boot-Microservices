package com.microservices.product.service;

import com.microservices.product.entities.Product;
import com.microservices.product.persistence.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> finAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        // Validar si el producto no existe, en caso de que no, lanzar exception
            // Exception
        //En caso de que si, actualizar
        productRepository.save(product);
    }
}
