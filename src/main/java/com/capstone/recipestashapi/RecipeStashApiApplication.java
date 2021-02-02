package com.capstone.recipestashapi;

import com.capstone.recipestashapi.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class RecipeStashApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeStashApiApplication.class, args);
	}

}

