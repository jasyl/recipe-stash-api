package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = "/recipes", produces = "application/json")
    public List<Recipe> getRecipes(@PathVariable long userId ) {
        return recipeService.getRecipes(userId);
    }

    @GetMapping(path = "/recipes/{recipeId}", produces = "application/json")
    public Recipe getRecipe(@PathVariable long userId, @PathVariable long recipeId) {
        return recipeService.getRecipe(userId, recipeId);
    }

    @PostMapping(path = "/recipes")
    public Recipe getRecipesExternal(@PathVariable long userId, @RequestParam String url) throws JsonProcessingException {
        return recipeService.addExternalRecipe(userId, url);
    }

    @PostMapping(path = "/recipes", consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@PathVariable long userId, @RequestBody Recipe recipe) {
        return recipeService.createRecipe(userId, recipe);
    }

    @PutMapping(path = "/recipes/{recipeId}")
    public void updateRecipe(@PathVariable long userId, @PathVariable long recipeId, @RequestBody Recipe newRecipe) {
        recipeService.updateRecipe(userId, recipeId, newRecipe);
    }

    @DeleteMapping(path = "/recipes/{recipeId}")
    public void deleteRecipe(@PathVariable long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }




}
