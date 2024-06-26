package com.microservices.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductIngredientRequestDTO {

    @NotNull
    private Long ingredientId;
    @NotNull
    private Double quantity;


}
