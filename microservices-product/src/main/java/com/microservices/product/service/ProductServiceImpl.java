package com.microservices.product.service;

import com.microservices.product.entities.Product;
import com.microservices.product.entities.ProductIngredients;
import com.microservices.product.persistence.IProductIngredientsRepository;
import com.microservices.product.persistence.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;
    private final IProductIngredientsRepository productIngredientRepository;

    public ProductServiceImpl(IProductRepository productRepository, IProductIngredientsRepository productIngredientRepository) {
        this.productRepository = productRepository;
        this.productIngredientRepository = productIngredientRepository;
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

    @Override
    public Product updateProductIngredients(Long productId, List<Long> ingredientIds, List<Double> quantities) {
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new RuntimeException("Product Not Found"));

        // Eliminar los ingredientes actuales
        productIngredientRepository.deleteAll(productIngredientRepository.findByProductId(productId));

        // Agregar los nuevos ingredientes
        for (int i = 0; i < ingredientIds.size(); i++) {
            Long ingredientId = ingredientIds.get(i);
            Double quantity = quantities.get(i);

            ProductIngredients productIngredients = new ProductIngredients();
            productIngredients.setProductId(productId);
            productIngredients.setIngredientId(ingredientId);
            productIngredients.setQuantity(quantity);

            productIngredientRepository.save(productIngredients);
        }

        // Actualizo el producto
        product.setProductIngredients(productIngredientRepository.findByProductId(productId));

        return productRepository.save(product);
    }
}
