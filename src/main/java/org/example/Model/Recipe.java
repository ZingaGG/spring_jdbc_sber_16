package org.example.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Recipe {
    private long id;
    private String name;
    private List<Ingredient> ingredients;

    public Recipe(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
