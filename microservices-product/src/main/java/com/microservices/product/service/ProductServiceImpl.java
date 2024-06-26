package com.microservices.product.service;

import com.microservices.product.dto.ProductIngredientRequestDTO;
import com.microservices.product.entities.Product;
import com.microservices.product.entities.ProductIngredients;
import com.microservices.product.persistence.IProductIngredientsRepository;
import com.microservices.product.persistence.IProductRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;
    private final IProductIngredientsRepository productIngredientRepository;

    public ProductServiceImpl(IProductRepository productRepository, IProductIngredientsRepository productIngredientRepository) {
        this.productRepository = productRepository;
        this.productIngredientRepository = productIngredientRepository;
    }

    @Override
    public List<Product> findAll() {
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
    public Product updateProductIngredients(Long productId, List<ProductIngredientRequestDTO> ingredients) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        productIngredientRepository.deleteAll(product.getProductIngredients());

        List<ProductIngredients> newIngredients = ingredients.stream()
                .map(request -> {
                    ProductIngredients productIngredient = new ProductIngredients();
                    productIngredient.setProduct(product);
                    productIngredient.setIngredientId(request.getIngredientId());
                    productIngredient.setQuantity(request.getQuantity());
                    return productIngredient;
                }).collect(Collectors.toList());

        product.setProductIngredients(newIngredients);
        return productRepository.save(product);

    }


}
