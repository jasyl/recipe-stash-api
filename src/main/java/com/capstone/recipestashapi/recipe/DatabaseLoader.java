package com.capstone.recipestashapi.recipe;

import com.capstone.recipestashapi.model.User;
import com.capstone.recipestashapi.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.util.List;

public class DatabaseLoader implements CommandLineRunner {

    private final RecipeService recipeService;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(RecipeService recipeService, UserRepository userRepository) {
        this.recipeService = recipeService;
        this.userRepository = userRepository;
    }

    public void run(String... strings) throws Exception {
        User jim = new User("jim halbert",  "jimhalbert@dundermifflin.com");
        userRepository.save(jim);

        User sara = new User("sara lee", "slee@ggmail.com");
        userRepository.save(sara);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static/data/recipes.json").getFile());
//
        ObjectMapper objectMapper = new ObjectMapper();

        List<Recipe> recipes = objectMapper.readValue(file, new TypeReference<List<Recipe>>(){});
        for (Recipe recipe : recipes) {
            recipeService.createRecipe(sara.getId(), recipe);
        }
    }
}
