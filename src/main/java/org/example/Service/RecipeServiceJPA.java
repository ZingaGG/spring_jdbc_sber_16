package org.example.Service;

import lombok.RequiredArgsConstructor;
import org.example.DAO.RecipeDAOEntityManager;
import org.example.Model.Recipe;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceJPA {

    private final RecipeDAOEntityManager recipeDAOEntityManager;

    public Recipe getRecipeByID(Long id){
        return recipeDAOEntityManager.getRecipeById(id).orElseThrow(RuntimeException::new);
    }
}
