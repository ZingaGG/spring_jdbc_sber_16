package org.example;

import org.example.Model.Ingredient;
import org.example.Model.Recipe;
import org.example.Service.RecipeServiceJPA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(org.example.Config.AppConfig.class);
        RecipeServiceJPA recipeServiceJPA = context.getBean(RecipeServiceJPA.class);

        List<Ingredient> ingredients = List.of(new Ingredient("Tomato", 2), new Ingredient("Water", 1));
        Recipe recipe = new Recipe("Pasta", ingredients);

        // Сохранение
        recipeServiceJPA.saveRecipe(recipe);

        // получение по айди с репозитория
        Recipe recieved = recipeServiceJPA.getRecipeByID(1L);

        System.out.println(recieved.getName());
        System.out.println(recieved.getIngredients());

        recipe.setName("Coffee");
        // апдейт
        recipeServiceJPA.updateRecipe(recipe);

        System.out.println("Поиск по полному названию - " + recipeServiceJPA.searchRecipesByName("Coffee"));
        System.out.println("Поиск по частичном названию - " + recipeServiceJPA.searchRecipesByNameContaining("Cof"));

        // Удаление
        recipeServiceJPA.deleteRecipeByID(1L);

        System.out.println(recipeServiceJPA.getRecipeByID(1L));
    }
}