package com.capstone.recipestashapi.recipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RecipeConfig {

    @Bean
    CommandLineRunner commandLineRunner(RecipeRepository repository){
        return args -> {
            Recipe recipe1 = new Recipe(
                    1L,
                    "Recipe Example",
                    30,
                    2,
                    "Ingredients",
                    "Instructions"
            );

            Recipe recipe2 = new Recipe(
                    "Recipe Example 2",
                    60,
                    4,
                    "Ingredients2",
                    "Instructions2"
            );

            repository.saveAll(
                    List.of(recipe1, recipe2)
            );

        };
    }
}
