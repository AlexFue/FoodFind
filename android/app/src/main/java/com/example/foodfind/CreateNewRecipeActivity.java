package com.example.foodfind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateNewRecipeActivity extends AppCompatActivity {

    private API api;
    private String recipeName;
    private String recipeDescription;
    private String imageURL;
    private int userId;
    private int foodListId;
    private int recipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_recipe);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createNewRecipe();
            }
        });
    }

    private void createNewRecipe() {

        //userId -> is passed to this activity

        //userId=
        recipeName = findViewById(R.id.recipeNameId).toString();
        recipeDescription = findViewById(R.id.descriptionId).toString();
        imageURL = findViewById(R.id.ImageId).toString();

        Recipe recipe = new Recipe(userId, recipeName, recipeDescription, imageURL);
        //Adds new recipe to Recipe
        Call<Recipe> call = api.createRecipe(recipe);

        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                Recipe recipeResponse = response.body();
                recipeId = recipeResponse.getRecipeId();
//                String content = "";
//                content += "Code: " + response.code() + "\n";
//                content += "Recipe Id: " + recipeResponse.getRecipeId();

                //Some textview to show the content and response code
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                //Some textview to show the error message, t.getMessage()
            }
        });

        //foodListId ->
        Call<Recipe> call2 = api.getFoodListByUserId(userId);
        call2.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                //Get the foodListId
                //Need to create a new class for FoodList problem is Recipe!
                //foodListId = response.body().getFoodListId();

            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                //Some textview to show the error message, t.getMessage()
            }
        });

        //Adds new recipe to foodList
//        Call<Recipe> call3 = api.addRecipeToFoodList(userId, foodListId, recipeId);
    }
}
