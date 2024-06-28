package com.microservices.product.feingclient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDTO {

    private Long id;
    private String name;
    private Double amount;
    private String unitOfMeasure;

}
