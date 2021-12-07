package com.example.foodfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ProfilePage extends AppCompatActivity implements Serializable {
    public Button profileBtn, addNewRecipeBtn;
    public User userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);

        profileBtn = findViewById(R.id.profileBtn);
        addNewRecipeBtn = findViewById(R.id.addNewRecipeBtn);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.profileBtn){
                    Intent i = new Intent(view.getContext(), ProfilePage.class);
                    startActivity(i);
                }
            }
        });
        addNewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.addNewRecipeBtn){
                    Intent i = new Intent(view.getContext(), CreateNewRecipeActivity.class);
                    //trying to pass the userId here
                    i.putExtra("userId", (Serializable) userId);
                    startActivity(i);
                    System.out.println(userId);
                }
            }
        });
    }
}
