package org.example.Service;

import org.example.DAO.RecipeDAO;
import org.example.Model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceJDBC {

    private final RecipeDAO recipeDAO;

    @Autowired
    public RecipeServiceJDBC(RecipeDAO recipeDAO) {
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

    public Recipe getRecipeById(long recipeId) {
        return recipeDAO.getRecipeById(recipeId);
    }

    public boolean addIngredientToRecipe(long recipeId, String ingredientName, double quantity) {
        return recipeDAO.addIngredientToRecipe(recipeId, ingredientName, quantity);
    }
}