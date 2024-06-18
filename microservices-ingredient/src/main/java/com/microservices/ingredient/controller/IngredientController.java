package com.microservices.ingredient.controller;

import com.microservices.ingredient.entities.Ingredient;
import com.microservices.ingredient.service.IIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IIngredientService ingredientService;

    public IngredientController(IIngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return ResponseEntity.ok(ingredientService.findByName(name));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveIngredient(@RequestBody Ingredient ingredient){
        ingredientService.save(ingredient);
    }


}
