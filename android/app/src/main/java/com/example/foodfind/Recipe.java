package com.example.foodfind;

public class Recipe {
    private int recipeId;
    private User user;
//    private int userId;
    private String name;
    private String description;
    private String image;

    public int getRecipeId(){
        return recipeId;
    }

    public int getUserId() {
        return user.getUserId();
    }

    public String getUsername() {
        return user.getUsername();
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

    public Recipe(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
