package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private Button loginBtn1;
    private EditText userEdit;
    private String username;
    private EditText passwordEdit;
    private String password;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        loginBtn1 = findViewById(R.id.btnLogin1);

        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btnLogin1){
//                    Intent i = new Intent(view.getContext(), Login.class);
//                    startActivity(i);
                    loginUser();

                }
            }
        });
    }

    private void loginUser() {
        userEdit = findViewById(R.id.etLoginEmail);
        username = userEdit.getText().toString();
        passwordEdit = findViewById(R.id.etLoginPassword);
        password = userEdit.getText().toString();

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
                System.out.println(user.getUserId());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}