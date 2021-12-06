package com.example.foodfind;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateNewRecipeActivity extends AppCompatActivity {

    private API api;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private String recipeName;
    private String recipeDescription;
    private String imageURL;
    private int userId = 1;
    private User user;
    private int foodListId;
    private int recipeId;
    private TextView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_recipe);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
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

        //userId is passed to this activity
        edit1 = findViewById(R.id.recipeNameId);
        recipeName = edit1.getText().toString();
        edit2 = findViewById(R.id.descriptionId);
        recipeDescription = edit2.getText().toString();
        edit3 = findViewById(R.id.ImageId);
        imageURL = edit3.getText().toString();

        //Adds new recipe to the Recipe List

        Call<Recipe> call = api.createNewRecipe(recipeName, recipeDescription, imageURL, userId);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {

                if (!response.isSuccessful()) {
                    view = findViewById(R.id.titleId);
                    System.out.println(response.code());
                    return;
                }

                Recipe recipeResponse = response.body();
                recipeId = recipeResponse.getRecipeId();
                System.out.println(recipeId);
                getFoodListId();
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                view = findViewById(R.id.titleId);
                view.setText(t.getMessage());
                System.out.println(t.getMessage());
            }
        });
    }

    private void getFoodListId() {
        //Get the foodListId by using the api call
        Call<FoodList> call2 = api.getFoodListByUserId(userId);
        call2.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call2, Response<FoodList> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }

                FoodList foodlists = response.body();
                foodListId = foodlists.getFoodListId();
                System.out.println(foodListId);
                addNewRecipeToFoodList();
            }

            @Override
            public void onFailure(Call<FoodList> call2, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void addNewRecipeToFoodList() {
        System.out.println("user Id: " + userId);
        System.out.println("foodlist Id: " + foodListId);
        System.out.println("recipe Id: " + recipeId);

        Call<String> call3 = api.addRecipeToFoodList(userId, foodListId, recipeId);
        call3.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call3, Response<String> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }

                System.out.println(response.body());
                String message = response.body();
                onBackPressed(message);
            }

            @Override
            public void onFailure(Call<String> call3, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void onBackPressed(String message){
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(CreateNewRecipeActivity.this);

        builder.setMessage(message);
        builder.setTitle("Response ");
        builder.setCancelable(false);

        builder.setNegativeButton(
                "OK",
                new DialogInterface
                        .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
