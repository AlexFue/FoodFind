package com.example.foodfind;

import java.util.ArrayList;
import java.util.List;

public class FoodList {
    private int foodListId;
    private int userId;
    private List<Recipe> recipes;

    public int getFoodListId() {
        return foodListId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
