package com.example.foodfind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewRecipesActivity extends AppCompatActivity{
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Recipe>> call = api.getRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Recipe> recipes = response.body();

                for (Recipe recipe : recipes) {
                    String content = "";
                    content += "User Id: " + recipe.getUserId() + "\n";
                    content += "Username: " + recipe.getUsername() + "\n";
                    content += "Name: " + recipe.getName() + "\n";
                    content += "Description " + recipe.getDescription() + "\n";
                    content += "Image" + recipe.getImage() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
