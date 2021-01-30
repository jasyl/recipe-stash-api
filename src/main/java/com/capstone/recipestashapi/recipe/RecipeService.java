package com.capstone.recipestashapi.recipe;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, IngredientsRepository ingredientsRepository1) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.ingredientsRepository = ingredientsRepository1;
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
    @Transactional
    public void createRecipe(long userId, Recipe recipe) {

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        recipe.setUser(user);
        Recipe recipeSaved = recipeRepository.save(recipe);
        user.addRecipe(recipeSaved);



    }
}
