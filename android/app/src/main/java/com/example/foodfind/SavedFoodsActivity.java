package com.example.foodfind;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SavedFoodsActivity extends AppCompatActivity {


    private API api;
    private TextView tvSavedFoods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_foods);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tvSavedFoods = findViewById(R.id.tvSavedFoods);
        api = retrofit.create(API.class);
        getSavedFoods();
    }

    public void getSavedFoods(){
        Call<SavedFood> call = api.getSavedFoods(28);

        call.enqueue(new Callback<SavedFood>() {
            @Override
            public void onResponse(Call<SavedFood> call, Response<SavedFood> response) {
                if (!response.isSuccessful()) {
                    tvSavedFoods = findViewById(R.id.tvSavedFoods);
                    System.out.println(response.code());
                    return;
                }
                System.out.println("Response not broken yet");
                for (int i = 0; i < response.body().getRecipes().size(); i++) {
                    String content = "";
                    content += "Recipe Name: " + response.body().getRecipes().get(i).getName() + "\n";
                    System.out.println(response.body().getRecipes().get(i).getName());
                    content += "Description: " + response.body().getRecipes().get(i).getDescription() + "\n";
                    System.out.println(response.body().getRecipes().get(i).getDescription());
                    tvSavedFoods.append(content);
                }
            }

            @Override
            public void onFailure(Call<SavedFood> call, Throwable t) {
                tvSavedFoods = findViewById(R.id.tvSavedFoods);
                tvSavedFoods.setText(t.getMessage());
                System.out.println("Breaking!: " + t.getMessage());
            }
        });
    }
}
