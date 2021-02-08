package com.capstone.recipestashapi.controller;


import com.capstone.recipestashapi.recipe.ExtendedIngredient;
import com.capstone.recipestashapi.recipe.Ingredient;
import com.capstone.recipestashapi.recipe.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<Ingredient> parseIngredients(@RequestParam("ingredients") String ingredients) throws JsonProcessingException {
        return ingredientService.parseIngredients(ingredients);
    }
}
