package com.capstone.recipestashapi.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // data access object DAO
// this class allows us to call methods like findbyID -> refer to the JpaRepostiory library
// https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findRecipeBySourceUrl(String url);
}