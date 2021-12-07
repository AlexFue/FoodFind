package com.example.foodfind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences userPref;
    private User userId;
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
                Intent i = new Intent(view.getContext(), Home.class);

                Bundle bundle = new Bundle();
                bundle.putString("userId", String.valueOf(userId));
                i.putExtras(bundle);

                //i.putExtra("userId", (Parcelable) userId);

                startActivity(i);
                break;
            case R.id.signUpButton:
//                startActivity(FactoryIntent.getIntent(CreateActivity.class,getApplicationContext()));

                Intent a = new Intent(view.getContext(), CreateAnAccount.class);

                startActivity(a);
                break;
        }

    }
}
