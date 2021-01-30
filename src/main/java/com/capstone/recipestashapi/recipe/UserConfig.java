package com.capstone.recipestashapi.recipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        return args -> {
            User janet = new User( "Janet", "Bars", "janet@gmail.com" );

            repository.save(janet);

        };
    }
}
