package com.capstone.recipestashapi.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<Recipe> getRecipes(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        return user.getRecipes();
    }

    public Recipe getRecipe(long userId, long recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new IllegalStateException("recipe with id " + recipeId + " does not exist"));
        if (!user.getRecipes().contains(recipe)) {
            throw new IllegalStateException("user does not have that recipe" + recipe);
        }
        return recipe;
    }

    public void addRecipe(long userId, Recipe recipe) {


        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        user.addRecipe(recipe);
        recipeRepository.save(recipe);

    }
}
