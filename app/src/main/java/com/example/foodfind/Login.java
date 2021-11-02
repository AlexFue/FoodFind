package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    public Button loginBtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText user, password;
        user = findViewById(R.id.etLoginEmail);
        password = findViewById(R.id.etLoginPassword);
//        if(user != null && password != null){
//            Toast.makeText(this,"You logged in", Toast.LENGTH_LONG).show();
//        }

        loginBtn1 = findViewById(R.id.btnLogin1);

        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btnLogin1){
                    Intent i = new Intent(view.getContext(), Login.class);
                    startActivity(i);
                }
            }
        });
    }
}
