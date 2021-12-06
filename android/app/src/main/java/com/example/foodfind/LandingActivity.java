package com.example.foodfind;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        // Check if user is already logged in and take them to the home page
        userPref = getSharedPreferences("userPreferences", MODE_PRIVATE);
        if (!userPref.getString("username", "").equals("")) {
//            startActivity(FactoryIntent.getIntent(HomeActivity.class,getApplicationContext()));
        }

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
//                startActivity(FactoryIntent.getIntent(LoginActivity.class,getApplicationContext()));
                // pass in the userId here, I think
                break;
            case R.id.signUpButton:
//                startActivity(FactoryIntent.getIntent(CreateActivity.class,getApplicationContext()));
                break;
        }

    }
}
