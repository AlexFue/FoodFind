package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Login extends AppCompatActivity {
    public Button loginBtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText user, password;
        user = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);

        loginBtn1 = findViewById(R.id.btnLogin1);

        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(user, password);
                if(view.getId() == R.id.btnLogin1){
                    Intent i = new Intent(view.getContext(), Home.class);
                    startActivity(i);
                }
            }
        });
    }


}