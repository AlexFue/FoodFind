package com.example.foodfind;

public class Recipe {
    private int recipeId;
    private int userId;
    private String name;
    private String description;
    private String image;

    public int getRecipeId(){
        return recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Recipe(int userId, String name, String description, String image) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
