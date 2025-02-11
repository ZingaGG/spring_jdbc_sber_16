package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.Model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RecipeDAOEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Recipe> getRecipeById(Long id){
        Recipe recipe = entityManager.find(Recipe.class, id);
        return Optional.of(recipe);
    }
}
