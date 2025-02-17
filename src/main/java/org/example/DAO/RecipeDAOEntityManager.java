package org.example.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.Model.Recipe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RecipeDAOEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Optional<Recipe> getRecipeById(Long id){
        Recipe recipe = entityManager.find(Recipe.class, id);
        return Optional.ofNullable(recipe);
    }

    @Transactional
    public void save(Recipe recipe) {
        entityManager.persist(recipe);
        entityManager.joinTransaction();
    }

    @Transactional
    public void deleteById(Long id) {
        Recipe recipe = entityManager.find(Recipe.class, id);
        if (recipe != null) {
            entityManager.remove(recipe);
            System.out.println("Recipe deleted!");
        }
    }
}
