package com.microservices.product.controller;


import com.microservices.product.dto.ProductIngredientRequestDTO;
import com.microservices.product.entities.Product;
import com.microservices.product.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(productService.finAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> findByName(@PathVariable String productName){
        return ResponseEntity.ok(productService.findByName(productName));
    }

    @PutMapping("/{productId}/ingredients")
    public ResponseEntity<Product> updateProductIngredients(
            @PathVariable Long productId,
            @RequestBody ProductIngredientRequestDTO request){
        Product updatedProduct = productService.updateProductIngredients(productId, request.getIngredientIds(),
                request.getQuantities());
        return ResponseEntity.ok(updatedProduct);


        /*
            {
                "ingredientIds": [1, 2, 3],
                "quantities": ["100g", "200ml", "50g"]
            }

         */
    }

}
