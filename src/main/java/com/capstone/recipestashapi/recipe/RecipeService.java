package com.capstone.recipestashapi.recipe;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Recipe createRecipe(long userId, Recipe recipe) {

        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(recipe);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        recipe.setUser(user);
        Recipe recipeSaved = recipeRepository.save(recipe);
        user.addRecipe(recipeSaved);

        return recipeSaved;
    }

    @Transactional
    public void updateRecipe(long userId, long recipeId, Recipe newRecipe) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalStateException("recipe with id " + recipeId + " does not exist"));

        List<Recipe> userRecipes = user.getRecipes();

        if (!userRecipes.contains(recipe)) {
            throw new IllegalStateException("This recipe" + recipeId +  "doesn't exist for this user" + userId);
        }

        // readyInMinutes
        int minutes = newRecipe.getReadyInMinutes();
        if (minutes > 0 && !Objects.equals(recipe.getReadyInMinutes(), minutes)) { // i don't think i have to use object equals because i'm dealing with primitives
            recipe.setReadyInMinutes(minutes);
        }

        // servings
        int servings = newRecipe.getServings();
        if (servings > 0 && !Objects.equals(recipe.getServings(), servings)) {
            recipe.setServings(servings);
        }

        // img
        String img = newRecipe.getImg();
        if (img != null && img.length() > 0 && !Objects.equals(recipe.getImg(), img)) {
            recipe.setImg(img);
        }

        // sourceUrl
        String src = newRecipe.getSourceUrl();
        if (src != null && src.length() > 0 && !Objects.equals(recipe.getSourceUrl(), src)) {
            recipe.setSourceUrl(src);
        }

        // title
        String title = newRecipe.getTitle();
        if (title != null && title.length() > 0 && !Objects.equals(recipe.getTitle(), title)) {
            recipe.setTitle(title);
        }

        // instructions
        String instructions = newRecipe.getInstructions();
        if (instructions != null && instructions.length() > 0 && !Objects.equals(recipe.getInstructions(), instructions)) {
            recipe.setInstructions(instructions);
        }

        // ingredients
        List<Ingredient> ingredients = newRecipe.getIngredients();
        if (ingredients != null && ingredients.size() > 0 && !Objects.equals(recipe.getIngredients(), ingredients)) {

            for (Ingredient ingredient : ingredients) {
                ingredient.setRecipe(recipe);
            }

            recipe.setIngredients(ingredients);
        }



    }
}
