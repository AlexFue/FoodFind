package com.example.foodfind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    Fragment homeFragment = new HomeFragment();
    Fragment createFragment = new CreateFragment();
    Fragment savedFragment = new SavedFragment();
    Fragment profileFragment = new ProfileFragment();

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                return true;

            case R.id.create:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, createFragment).commit();
                return true;

            case R.id.saved:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, savedFragment).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;
        }
        return false;
    }
}