package com.capstone.recipestashapi.recipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class RecipeController {

    @GetMapping
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
