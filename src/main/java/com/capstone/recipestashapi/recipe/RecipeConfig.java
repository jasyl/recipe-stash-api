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
            Recipe cookies = new Recipe(
                    null,
                    30,
                    2,
                    "img",
                    "src url",
                    "Cookie Recipe",
                    3847384,
                    "Instructions",
                    sara
            );

            User jim = new User("jim", "halbert", "jimhalbert@dundermifflin.com");
            userRepository.save(jim);
            Recipe cake = new Recipe(
                    null,
                    65,
                    3,
                    "img",
                    "src url",
                    "Cake Recipe",
                    34321,
                    "Instructions",
                    jim
            );

            Recipe ramen = new Recipe(
                    null,
                    30,
                    2,
                    "img",
                    "src url",
                    "Ramen Recipe",
                    3847384,
                    "Instructions",
                    sara
            );

            Recipe burger = new Recipe(
                    null,
                    30,
                    2,
                    "img",
                    "src url",
                    "Burger Recipe",
                    3847384,
                    "Instructions",
                    sara
            );

            recipeRepository.saveAll(
                    List.of(cookies, cake, ramen, burger)
            );

        };
    }
}
