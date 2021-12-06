package com.example.foodfind;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAnAccount extends AppCompatActivity {
    public Button createaccBtn;
    private API api;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private String name;
    private String username;
    private String password;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        createaccBtn = findViewById(R.id.btnCreateAccount);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);


        createaccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });
    }

    private void createNewUser(){

        edit1 = findViewById(R.id.etFirstName);
        name = edit1.getText().toString();
        System.out.println(name);

        edit2 = findViewById(R.id.etCreateEmail);
        username = edit2.getText().toString();
        System.out.println(username);

        edit3 = findViewById(R.id.etCreatePassword);
        password = edit3.getText().toString();
        System.out.println(password);

        Call<User> call = api.createNewUser(name, username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }

                User newUser = response.body();
                userId = newUser.getUserId();
                System.out.println(userId);
                //                Intent i = new Intent(this, Login.class);
//                i.putExtra(EXTRA_MESSAGE, userId);
//                startActivity(i);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
