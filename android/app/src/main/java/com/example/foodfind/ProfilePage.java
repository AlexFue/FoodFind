package com.example.foodfind;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilePage extends AppCompatActivity {

    private API api;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        getUserFoodList();

    }

    private void getUserFoodList() {
        userId = 1;

        Call<FoodList> call = api.getFoodListByUserId(userId);

        call.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }

                FoodList foodlists = response.body();
                List<Recipe> recipes = foodlists.getRecipes();
                for (Recipe recipe : recipes) {
                    System.out.println("recipe ID: " + recipe.getRecipeId());
                }
            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
