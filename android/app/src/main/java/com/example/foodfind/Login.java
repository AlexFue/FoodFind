package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.converter.gson.GsonConverterFactory;


public class Login extends AppCompatActivity {
    private Button loginBtn1;
    private EditText userEdit;
    private String username;
    private EditText passwordEdit;
    private String password;
    private int userId;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginBtn1 = findViewById(R.id.btnLogin1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);


        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(user, password);

                    loginUser();

            }
        });
    }

    private void loginUser() {

        userEdit = findViewById(R.id.etLoginEmail);
        username = userEdit.getText().toString();
        passwordEdit = findViewById(R.id.etLoginPassword);
        password = userEdit.getText().toString();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        Call<User> call = api.loginUser(username, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;

                }

                //200-300
                User user = response.body();
                userId = user.getUserId();
                System.out.println(user.getUserId());
                Intent i = new Intent(Login.this, Home.class);
                i.putExtra("UserId", userId);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}