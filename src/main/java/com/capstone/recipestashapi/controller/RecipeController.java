package com.capstone.recipestashapi.controller;

import com.capstone.recipestashapi.exception.ResourceNotFoundException;
import com.capstone.recipestashapi.model.User;
import com.capstone.recipestashapi.recipe.Recipe;
import com.capstone.recipestashapi.recipe.RecipeService;
import com.capstone.recipestashapi.repository.UserRepository;
import com.capstone.recipestashapi.security.CurrentUser;
import com.capstone.recipestashapi.security.UserPrincipal;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserRepository userRepository;

    @Autowired
    public RecipeController(RecipeService recipeService, UserRepository userRepository) {
        this.recipeService = recipeService;
        this.userRepository = userRepository;
    }

    @GetMapping(produces = "application/json")
    public List<Recipe> getRecipes(@CurrentUser UserPrincipal userPrincipal ) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return recipeService.getRecipes(user.getId());
    }

    @GetMapping(path = "/{recipeId}", produces = "application/json")
    public Recipe getRecipe(@PathVariable long userId, @PathVariable long recipeId) {
        return recipeService.getRecipe(userId, recipeId);
    }

    @PostMapping(produces = "application/json")
//    @PreAuthorize("hasRole('USER')")
    public Recipe getRecipesExternal(@CurrentUser UserPrincipal userPrincipal, @RequestParam("url") String url) throws JsonProcessingException {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return recipeService.addExternalRecipe(user.getId(), url);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Recipe addRecipe(@CurrentUser UserPrincipal userPrincipal, @RequestBody Recipe recipe) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        return recipeService.createRecipe(user.getId(), recipe);
    }

    @PutMapping(path = "/{recipeId}")
    public void updateRecipe(@CurrentUser UserPrincipal userPrincipal, @PathVariable long recipeId, @RequestBody Recipe newRecipe) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        recipeService.updateRecipe(user.getId(), recipeId, newRecipe);
    }

    @DeleteMapping(path = "/{recipeId}")
    public void deleteRecipe(@PathVariable long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }




}
