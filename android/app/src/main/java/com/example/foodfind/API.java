package com.example.foodfind;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @GET("view_recipes")
    Call<List<Recipe>> getRecipes();

    @POST("create_food")
    Call<Recipe> createRecipe(@Body Recipe recipe);

    @POST("create_food_by_user/{id1}/{id2}/{id3}")
    Call<Recipe> addRecipeToFoodList(@Path("id1") int userId, @Path("id2") int foodListId, @Path("id3") int recipeId);

    @GET("view_foodlist_by_userId/{id}")
    Call<Recipe> getFoodListByUserId(@Path("id") int userId);

}
