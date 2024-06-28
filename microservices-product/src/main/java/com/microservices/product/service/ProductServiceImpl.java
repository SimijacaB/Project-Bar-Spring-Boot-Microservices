package com.microservices.product.service;

import com.microservices.product.dto.ProductIngredientRequestDTO;
import com.microservices.product.entities.Product;
import com.microservices.product.entities.ProductIngredients;
import com.microservices.product.feingclient.IngredientClient;
import com.microservices.product.feingclient.IngredientDTO;
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

    private final IngredientClient ingredientClient;

    public ProductServiceImpl(IProductRepository productRepository, IProductIngredientsRepository productIngredientRepository, IngredientClient ingredientClient) {
        this.productRepository = productRepository;
        this.productIngredientRepository = productIngredientRepository;
        this.ingredientClient = ingredientClient;
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
                    //Obtengo la información del ingrediente usando Feign Client
                    IngredientDTO ingredientDTO = ingredientClient.getIngredientById(request.getIngredientId());

                    ProductIngredients productIngredients = new ProductIngredients();
                    productIngredients.setProduct(product);
                    productIngredients.setIngredientId(ingredientDTO.getId());
                    productIngredients.setQuantity(request.getQuantity());
                    return productIngredients;
                }).toList();

        product.setProductIngredients(newIngredients);
        return productRepository.save(product);

    }


}
