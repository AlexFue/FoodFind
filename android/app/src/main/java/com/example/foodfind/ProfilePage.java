package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    public Button profileBtn, addNewRecipeBtn;
    public int userId;
    private API api;
    public final static String EXTRA_MESSAGE = "com.example.foodfind.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);


//        Intent intent = getIntent();
//        String message = intent.getStringExtra(Home.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.homeRecipe1);
//        textView.setText(message);

        profileBtn = findViewById(R.id.profileBtn);
        addNewRecipeBtn = findViewById(R.id.addNewRecipeBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.profileBtn){
                    Intent i = new Intent(view.getContext(), ProfilePage.class);
                    startActivity(i);
                }
            }
        });
        addNewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.addNewRecipeBtn){
                    Intent i = new Intent(view.getContext(), CreateNewRecipeActivity.class);
                    //trying to pass the userId here
                    i.putExtra("userId", (Serializable) userId);
                    startActivity(i);
                    System.out.println(userId);
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

//        getUserFoodList();
//        Bundle profileBtn = new Bundle();
//        String receivingdata = profileBtn.getString("Key");
//        TextView tv = (TextView) findViewById(R.id.homeRecipe1);
//        tv.setText(receivingdata);


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
                    System.out.println("recipe name" + recipe.getName());

                }
            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}


