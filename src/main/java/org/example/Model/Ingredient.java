package org.example.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ingredient {
    private String name;
    private double quantity;

    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
