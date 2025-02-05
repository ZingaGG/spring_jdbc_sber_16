package org.example.Service;

import org.example.DAO.RecipeDAO;
import org.example.Model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeDAO recipeDAO;

    @Autowired
    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public void addRecipe(String name) {
        recipeDAO.addRecipe(name);
    }

    public List<Recipe> getAllRecipes() { return recipeDAO.getAllRecipes(); }

    public List<Recipe> searchRecipesByName(String namePart) {
        return recipeDAO.searchRecipesByName(namePart);
    }

    public void deleteRecipe(long recipeId) {
        recipeDAO.deleteRecipe(recipeId);
    }

    public Recipe getRecipeWithIngredients(long recipeId) {
        return recipeDAO.getRecipeWithIngredients(recipeId);
    }

    public void addIngredientToRecipe(long recipeId, String ingredientName, double quantity) {
        recipeDAO.addIngredientToRecipe(recipeId, ingredientName, quantity);
    }
}