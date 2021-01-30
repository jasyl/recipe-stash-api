package com.capstone.recipestashapi.recipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RecipeConfig {

    @Bean
    CommandLineRunner recipeCommandLineRunner(RecipeRepository recipeRepository, UserRepository userRepository){
        return args -> {
            User sara = new User("sara", "lee", "slee@ggmail.com");
            userRepository.save(sara);
            Recipe recipe1 = new Recipe(
                    null,
                    30,
                    2,
                    "img",
                    "src url",
                    "Recipe Example",
                    3847384,
                    "Instructions",
                    sara
            );

            User jim = new User("jim", "halbert", "jimhalbert@dundermifflin.com");
            userRepository.save(jim);
            Recipe recipe2 = new Recipe(
                    null,
                    65,
                    3,
                    "img",
                    "src url",
                    "Another Recipe Example",
                    34321,
                    "Instructions",
                    jim
            );

            recipeRepository.saveAll(
                    List.of(recipe1, recipe2)
            );

        };
    }
}
