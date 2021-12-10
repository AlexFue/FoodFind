package com.example.foodfind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodfind.fragments.CreateFragment;
import com.example.foodfind.fragments.HomeFragment;
import com.example.foodfind.fragments.ProfileFragment;
import com.example.foodfind.fragments.SavedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences userPref;
    private User userId;
    private int userId1;
    private BottomNavigationView bottomNavigationView;
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
        bottomNavigationView = findViewById(R.id.bottom_navigation1);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod1);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod1 =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment frag = null;
                    switch (item.getItemId()) {
                        case R.id.home:
                            frag=new HomeFragment();
                            Intent i = new Intent(LandingActivity.this, Home.class);
                            startActivity(i);

                            break;
                        case R.id.create:
                            frag=new CreateFragment();
                            Intent a = new Intent(LandingActivity.this, CreateNewRecipeActivity.class);
                            startActivity(a);
                            break;
                        case R.id.saved:
                            frag=new SavedFragment();
                            Intent b = new Intent(LandingActivity.this, SavedFoodsActivity.class);
                            startActivity(b);
                            break;
                        case R.id.profile:
                            frag=new ProfileFragment();
                            Intent c = new Intent(LandingActivity.this, ProfilePage.class);
                            startActivity(c);
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, frag).commit();
                    return true;
                }
            };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                Intent i = new Intent(view.getContext(), Home.class);

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
