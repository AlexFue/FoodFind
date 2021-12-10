package com.example.foodfind;

import java.util.List;

public class SavedFood {
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public SavedFood(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
