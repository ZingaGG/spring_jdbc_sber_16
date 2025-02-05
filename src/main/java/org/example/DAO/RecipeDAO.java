package org.example.DAO;

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

    public void deleteRecipe(long recipeId) {
        String deleteIngredientsSql = "DELETE FROM ingredients WHERE recipe_id = ?";
        String deleteRecipeSql = "DELETE FROM recipes WHERE id = ?";
        jdbcTemplate.update(deleteIngredientsSql, recipeId);
        jdbcTemplate.update(deleteRecipeSql, recipeId);
    }

    private static class RecipeRowMapper implements RowMapper<Recipe> {
        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Recipe(id, name);
        }
    }
}