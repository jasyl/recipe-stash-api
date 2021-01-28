package com.capstone.recipestashapi.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // data access
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
