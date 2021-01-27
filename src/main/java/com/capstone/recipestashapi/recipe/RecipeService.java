package com.capstone.recipestashapi.recipe;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    public List<Recipe> getRecipes() {
        return List.of(
                new Recipe(

                        30,
                        2,
                        "Ingredients",
                        "Instructions"
                )
        );
    }
}
