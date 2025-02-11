package org.example;

import org.example.Service.RecipeServiceJDBC;
import org.example.Service.RecipeServiceJPA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(org.example.Config.AppConfig.class);
        RecipeServiceJPA recipeServiceJPA = context.getBean(RecipeServiceJPA.class);

        System.out.println(recipeServiceJPA.getRecipeByID(1L));

    }
}