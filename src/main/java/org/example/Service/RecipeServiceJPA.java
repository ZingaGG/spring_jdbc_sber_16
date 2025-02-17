package org.example.Service;


import lombok.RequiredArgsConstructor;
import org.example.DAO.RecipeDAOEntityManager;
import org.example.Model.Recipe;
import org.example.Repository.IRecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceJPA{

    private final IRecipeRepository iRecipeRepository;
    private final RecipeDAOEntityManager recipeDAOEntityManager;

    @Transactional
    public Recipe getRecipeByID(long id){
        return recipeDAOEntityManager.getRecipeById(id)
                .orElseGet(() -> {
            System.out.println("The Recipe does not exist!");
            return null;
        });
    }

    @Transactional
    public void saveRecipe(Recipe recipe){
        recipeDAOEntityManager.save(recipe);
    }

    @Transactional
    public void deleteRecipeByID(long id){
        recipeDAOEntityManager.deleteById(id);
    }

    @Transactional
    public void updateRecipe(Recipe recipe){
        iRecipeRepository.save(recipe);
    }

    @Transactional(readOnly = true)
    public List<Recipe> searchRecipesByName(String name) {
        return iRecipeRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Recipe> searchRecipesByNameContaining(String name) {
        return iRecipeRepository.findByNameContaining(name);
    }
}
