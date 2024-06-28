package com.microservices.product.controller;


import com.microservices.product.dto.ProductIngredientRequestDTO;
import com.microservices.product.entities.Product;
import com.microservices.product.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product){
        productService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return ResponseEntity.ok(productService.findByName(name));
    }

    @PutMapping("/update/{productId}/ingredients")
    public ResponseEntity<Product> updateProductIngredients(
            @PathVariable Long productId,
            @RequestBody List<ProductIngredientRequestDTO> ingredients) {
        try {
            Product updatedProduct = productService.updateProductIngredients(productId, ingredients);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            e.printStackTrace();  // Para imprimir la traza del error en los logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
