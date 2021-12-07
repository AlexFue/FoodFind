package com.example.foodfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodfind.fragments.CreateFragment;
import com.example.foodfind.fragments.HomeFragment;
import com.example.foodfind.fragments.ProfileFragment;
import com.example.foodfind.fragments.SavedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag = null;
            switch (item.getItemId()) {
                case R.id.home:
                    frag=new HomeFragment();
                    Intent i = new Intent(NavActivity.this, Home.class);
                    startActivity(i);

                    break;
                case R.id.create:
                    frag=new CreateFragment();
                    break;
                case R.id.saved:
                    frag=new SavedFragment();
                    break;
                case R.id.profile:
                    frag=new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, frag).commit();
            return true;
        }
    };
}