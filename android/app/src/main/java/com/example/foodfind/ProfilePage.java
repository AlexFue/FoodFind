package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodfind.Home;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilePage extends AppCompatActivity implements Serializable {

    private int userId;
    private API api;
    private TextView viewUserId;
    private TextView viewUsername;
    private TextView viewUserFoodList;
    private Button homeButton;
    private Button addRecipeButton;
    private ImageView showRecipeImage;
    private String image;
    private Button removeRecipeButton;

    public final static String EXTRA_MESSAGE = "com.example.foodfind.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);

        Intent intent = getIntent();
        userId = intent.getIntExtra("UserId", 1);

        viewUserId = findViewById(R.id.viewUserId);
        viewUsername = findViewById(R.id.viewUsername);
        viewUserFoodList = findViewById(R.id.viewUserFoodList);
        homeButton = findViewById(R.id.homeButton);
        addRecipeButton = findViewById(R.id.addRecipeButton);

        Button button1 = (Button) homeButton;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
            }
        });

        Button button2 = (Button) addRecipeButton;
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateNewRecipeActivity.class);
                intent.putExtra("UserId", userId);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        getUserFoodList();
    }

    private void getUserFoodList() {
        userId = 15;

        Call<FoodList> call = api.getFoodListByUserId(userId);

        call.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }

                FoodList foodlists = response.body();
                viewUserId.setText("User Id: " + Integer.toString(userId));

                List<Recipe> recipes = foodlists.getRecipes();

                for (Recipe recipe : recipes) {
                    System.out.println("recipe ID: " + recipe.getRecipeId());
                    System.out.println("recipe name" + recipe.getName());

                    viewUsername.setText("Username: " + recipe.getUsername());
                    String content = "";
                    content += "Recipe Id: " + recipe.getUserId() + "\n";
                    content += "Recipe Name: " + recipe.getName() + "\n";
                    content += "Recipe Description: " + recipe.getDescription() + "\n";
                    content += "Recipe Image: " + recipe.getImage() + "\n\n";
                    viewUserFoodList.append(content);
                }
            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}