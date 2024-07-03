package com.vegan.ricette_vegetariane.service;

import com.vegan.ricette_vegetariane.dto.CommentDTO;
import com.vegan.ricette_vegetariane.dto.RecipeDTO;
import com.vegan.ricette_vegetariane.entity.Recipe;
import com.vegan.ricette_vegetariane.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream()
                .map(this::mapRecipeToDTO)
                .collect(Collectors.toList());
    }

    public Optional<RecipeDTO> getRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        return recipeOptional.map(this::mapRecipeToDTO);
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO, String username) {
        Recipe recipe = mapDTOToRecipe(recipeDTO);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapRecipeToDTO(savedRecipe);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    // Metodo di mapping da Recipe a RecipeDTO
    private RecipeDTO mapRecipeToDTO(Recipe recipe) {
        List<CommentDTO> commentDTOs = recipe.getComments().stream()
                .map(comment -> new CommentDTO(
                        comment.getId(),
                        comment.getComment(),
                        comment.getUser().getUsername(),
                        null
                ))
                .collect(Collectors.toList());

        return new RecipeDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getInstructions(),
                commentDTOs
        );
    }

    // Metodo di mapping da RecipeDTO a Recipe
    private Recipe mapDTOToRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setInstructions(recipeDTO.getInstructions());
        return recipe;
    }
}
