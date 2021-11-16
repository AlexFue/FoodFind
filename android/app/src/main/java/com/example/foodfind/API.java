package com.example.foodfind;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("view_foods")
    Call<List<Recipe>> getRecipes();

}
