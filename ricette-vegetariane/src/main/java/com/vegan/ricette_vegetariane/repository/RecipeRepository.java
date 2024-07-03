package com.vegan.ricette_vegetariane.repository;

import com.vegan.ricette_vegetariane.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthorUsername(String username);
}
