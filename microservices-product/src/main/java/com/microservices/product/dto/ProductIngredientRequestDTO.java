package com.microservices.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductIngredientRequestDTO {

    private List<Long> ingredientIds;

    private List<Double> quantities;


}
