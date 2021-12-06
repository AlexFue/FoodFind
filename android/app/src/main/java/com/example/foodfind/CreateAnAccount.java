package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAnAccount extends AppCompatActivity {
    public Button createaccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        createaccBtn = findViewById(R.id.btnCreateAccount);

        createaccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateAnAccount.this, Login.class);
                
                startActivity(i);
            }
        });
    }
}
