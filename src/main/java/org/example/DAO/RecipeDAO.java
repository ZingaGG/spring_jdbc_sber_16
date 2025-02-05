package org.example.DAO;

import org.example.Model.Ingredient;
import org.example.Model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RecipeDAO {

    private final JdbcTemplate jdbcTemplate;

    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addRecipe(String name) {
        String sql = "INSERT INTO recipes (name) VALUES (?)";
        jdbcTemplate.update(sql, name);
    }

    public List<Recipe> searchRecipesByName(String namePart) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), "%" + namePart + "%");
    }

    public Recipe getRecipeWithIngredients(long recipeId) {
        String recipeSql = "SELECT * FROM recipes WHERE id = ?";
        Recipe recipe = jdbcTemplate.queryForObject(recipeSql, new RecipeRowMapper(), recipeId);

        String ingredientsSql = "SELECT * FROM ingredients WHERE recipe_id = ?";
        List<Ingredient> ingredients = jdbcTemplate.query(ingredientsSql, new IngredientRowMapper(), recipeId);

        recipe.setIngredients(ingredients);
        return recipe;
    }

    public List<Recipe> getAllRecipes(){
        String sql = "SELECT * FROM recipes";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
    }

    public void deleteRecipe(long recipeId) {
        String deleteIngredientsSql = "DELETE FROM ingredients WHERE recipe_id = ?";
        String deleteRecipeSql = "DELETE FROM recipes WHERE id = ?";
        jdbcTemplate.update(deleteIngredientsSql, recipeId);
        jdbcTemplate.update(deleteRecipeSql, recipeId);
    }

    public void addIngredientToRecipe(long recipeId, String ingredientName, double quantity) {
        String sql = "INSERT INTO ingredients (recipe_id, ingredient_name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, recipeId, ingredientName, quantity);
    }

    private static class RecipeRowMapper implements RowMapper<Recipe> {
        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Recipe(id, name);
        }
    }

    private static class IngredientRowMapper implements RowMapper<Ingredient> {
        @Override
        public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("ingredient_name");
            double quantity = rs.getDouble("quantity");
            return new Ingredient(name, quantity);
        }
    }
}
