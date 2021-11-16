package com.example.foodfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// This page acts as the Home Page.
public class MainActivity extends AppCompatActivity {
    public Button loginBtn, create_accountBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.btnLogin);
//
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btnLogin){
                    Intent i = new Intent(view.getContext(), Login.class);
                    startActivity(i);
                }
            }
        });
        create_accountBtn = findViewById(R.id.btnCA);
        create_accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() ==  R.id.btnCA){
                    Intent i = new Intent(view.getContext(), CreateAnAccount.class);
                    startActivity(i);
                }
            }
        });

    }
}