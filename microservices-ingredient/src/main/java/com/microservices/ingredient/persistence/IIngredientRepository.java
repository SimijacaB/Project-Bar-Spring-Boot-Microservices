package com.microservices.ingredient.persistence;

import com.microservices.ingredient.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIngredientRepository extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findByName(String name);

}
