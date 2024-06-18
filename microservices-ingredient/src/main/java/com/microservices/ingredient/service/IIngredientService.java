package com.microservices.ingredient.service;

import com.microservices.ingredient.entities.Ingredient;

import java.util.List;

public interface IIngredientService {

    List<Ingredient> findAll();

    List<Ingredient> findByName(String name);

    void save(Ingredient ingredient);

}
