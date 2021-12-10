package com.example.foodfind;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @GET("view_recipes")
    Call<List<Recipe>> getRecipes();

    @POST("create_food")
    Call<Recipe> createRecipe(@Body Recipe recipe);

//    @POST("create_food_by_userId/{pk}")
//    Call<Recipe> createNewRecipe(@Body Recipe recipe, @Path("pk") int pk);

    @FormUrlEncoded
    @POST("create_food_by_userId/{pk}/")
    Call<Recipe> createNewRecipe(@Field("name") String name,  @Field("description") String description,
                                 @Field("image") String image, @Path("pk") int pk);

    @POST("create_food_by_user/{id1}/{id2}/{id3}")
    Call<String> addRecipeToFoodList(@Path("id1") int userId, @Path("id2") int foodListId, @Path("id3") int recipeId);

    @GET("view_foodlists_by_userId/{pk}")
    Call<FoodList> getFoodListByUserId(@Path("pk") int pk);

    @GET("view_foodlist")
    Call<List<FoodList>> getFoodList();

    //create-users-api/bob/bob12345/bob12345/
    @POST("create-users-api/{u_name}/{u_username}/{u_password}/")
    Call<User> createNewUser(@Path("u_name") String u_name, @Path("u_username") String u_username, @Path("u_password") String u_password);


//    login-api/<str:u_username>/<str:u_password>/

    @POST("login-api/{u_username}/{u_password}/")
    Call<User> loginUser(@Path("u_username") String username, @Path("u_password") String password);


    @GET("save/{userId}/get/")
    Call<SavedFood> getSavedFoods(@Path("userId") int userId);

//    {
//        userid = ,
//        name = ,
//        username =,
//        password =
//    }
}
