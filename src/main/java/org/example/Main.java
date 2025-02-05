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
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe name: ");
                    String name = scanner.nextLine();
                    recipeService.addRecipe(name);
                    System.out.println("Recipe added successfully.");
                    break;

                case 2:
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
                    break;

                case 3:
                    System.out.print("Enter recipe ID to delete: ");
                    long recipeId = scanner.nextLong();
                    recipeService.deleteRecipe(recipeId);
                    System.out.println("Recipe deleted successfully.");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}