package com.capstone.recipestashapi.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class RecipeConfig {

    private final RecipeService recipeService;

    @Autowired
    public RecipeConfig(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Bean
    CommandLineRunner recipeCommandLineRunner(RecipeRepository recipeRepository, UserRepository userRepository, IngredientsRepository ingredientsRepository){
        return args -> {

            User jim = new User("jim", "halpert", "jimhalbert@dundermifflin.com");
            userRepository.save(jim);

            User sara = new User("sara", "lee", "slee@ggmail.com");
            userRepository.save(sara);

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("static/data/recipes.json").getFile());
//
            ObjectMapper objectMapper = new ObjectMapper();

            List<Recipe> recipes = objectMapper.readValue(file, new TypeReference<List<Recipe>>(){});
            for (Recipe recipe : recipes) {
                recipeService.createRecipe(sara.getId(), recipe);
            }

        };
    }
}
