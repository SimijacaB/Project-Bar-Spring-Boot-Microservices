package com.microservices.product.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProductIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(name = "ingredient_id")
    private Long ingredientId;  // Utilizamos el ID del ingrediente

    private Double quantity;  // Cantidad del ingrediente en el producto (cantidad necesaria para la preparación de 1
    // producto

}
