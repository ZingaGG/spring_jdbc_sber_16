package org.example;

import org.example.Model.Recipe;
import org.example.Service.RecipeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(org.example.Config.AppConfig.class);
        RecipeService recipeService = context.getBean(RecipeService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Recipe");
            System.out.println("2. Search Recipes by Name");
            System.out.println("3. Delete Recipe");
            System.out.println("4. All recipes");
            System.out.println("5. Add Ingredient to Recipe");
            System.out.println("6. Get Recipe with Ingredients");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter recipe name: ");
                    String name = scanner.nextLine();
                    recipeService.addRecipe(name);
                    System.out.println("Recipe added successfully.");
                }
                case 2 -> {
                    System.out.print("Enter part of recipe name to search: ");
                    String namePart = scanner.nextLine();
                    List<Recipe> recipes = recipeService.searchRecipesByName(namePart);
                    if (recipes.isEmpty()) {
                        System.out.println("No recipes found.");
                    } else {
                        for (Recipe recipe : recipes) {
                            System.out.println(recipe);
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Enter recipe ID to delete: ");
                    long recipeId = scanner.nextLong();
                    recipeService.deleteRecipe(recipeId);
                    System.out.println("Recipe deleted successfully.");
                }
                case 4 -> {
                    List<Recipe> allRecipes = recipeService.getAllRecipes();
                    if (allRecipes.isEmpty()) {
                        System.out.println("No recipes found.");
                    } else {
                        for (Recipe recipe : allRecipes) {
                            System.out.println(recipe);
                        }
                    }
                }
                case 5 -> {
                    System.out.print("Enter recipe ID: ");
                    long recipeId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter ingredient name: ");
                    String ingredientName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    double quantity = scanner.nextDouble();
                    scanner.nextLine();
                    if (recipeService.addIngredientToRecipe(recipeId, ingredientName, quantity)){
                        System.out.println("Ingredient added successfully.");
                    }
                }

                case 6 -> {
                    System.out.print("Enter recipe ID to get with ingredients: ");
                    long id = scanner.nextLong();
                    Recipe recipe = recipeService.getRecipeById(id);
                    if (recipe != null) {
                        System.out.println(recipe);
                    }
                }

                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}