package com.capstone.recipestashapi.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}")
public class RecipeController {

    private final RecipeService recipeService;
    private final RestTemplate restTemplate;

    @Autowired
    public RecipeController(RecipeService recipeService, RestTemplate restTemplate) {
        this.recipeService = recipeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/recipes", produces = "application/json")
    public List<Recipe> getRecipes(@PathVariable long userId ) {
        return recipeService.getRecipes(userId);
    }

    @GetMapping(path = "/recipes/{recipeId}", produces = "application/json")
    public Recipe getRecipe(@PathVariable long userId, @PathVariable long recipeId) {
        return recipeService.getRecipe(userId, recipeId);
    }

    @PostMapping(path = "/recipes", consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@PathVariable long userId, @RequestBody Recipe recipe) {
        return recipeService.createRecipe(userId, recipe);
    }

    @PutMapping(path = "/recipes/{recipeId}")
    public void updateRecipe(@PathVariable long userId, @PathVariable long recipeId, @RequestBody Recipe newRecipe) {
        recipeService.updateRecipe(userId, recipeId, newRecipe);
    }

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping(path = "/{query}")
    public void getRecipesExternal(@PathVariable long userId, @PathVariable String query) {

        final String uri = "https://api.spoonacular.com/recipes/complexSearch" + "?apiKey=" + apiKey + "&query=" + query ;
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

    }
}
