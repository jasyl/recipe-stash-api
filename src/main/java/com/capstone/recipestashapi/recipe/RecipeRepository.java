package com.capstone.recipestashapi.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // data access
public interface StudentRepository extends JpaRepository<Recipe, Long> {

}
