package com.microservices.ingredient.service;

import com.microservices.ingredient.entities.Ingredient;
import com.microservices.ingredient.persistence.IIngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IIngredientService{

    private final IIngredientRepository ingredientRepository;

    public IngredientServiceImpl(IIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAll() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByName(name);
    }

    @Override
    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
