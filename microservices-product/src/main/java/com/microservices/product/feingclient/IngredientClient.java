package com.microservices.product.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-ingredient")
public interface IngredientClient {

    @GetMapping("/api/ingredients/{id}")
    IngredientDTO getIngredientById(@PathVariable Long id);

}
