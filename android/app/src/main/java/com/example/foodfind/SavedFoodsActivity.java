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

        api = retrofit.create(API.class);

        Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSavedFoods();
            }
        });
    }

    public void getSavedFoods(){
        Call<List<Recipe>> call = api.getSavedFoods(28);

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()) {
                    tvSavedFoods = findViewById(R.id.tvSavedFoods);
                    System.out.println(response.code());
                    return;
                }
                tvSavedFoods = findViewById(R.id.tvSavedFoods);
                for(int i = 0; i < response.body().size(); i++){
                    String content = "";
                    content += "Recipe Name: " + response.body().get(i).getName() + "\n";
                    content += "Description: " + response.body().get(i).getDescription() + "\n";
                    tvSavedFoods.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                tvSavedFoods = findViewById(R.id.tvSavedFoods);
                tvSavedFoods.setText(t.getMessage());
                System.out.println(t.getMessage());
            }
        });

    }
}
