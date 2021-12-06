package com.example.foodfind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditFoodActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_description;
    private EditText et_image;
    private Bundle bun;

    // on render, get name, description, and image of recipe from intent and set it.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        et_image = findViewById(R.id.et_image);
        bun = getIntent().getExtras();

        et_name.setText(bun.getString("name"), TextView.BufferType.EDITABLE);
        et_description.setText(bun.getString("description"), TextView.BufferType.EDITABLE);
        et_image.setText(bun.getString("image"), TextView.BufferType.EDITABLE);
    }

    public void update(View view) {
        String name = String.valueOf(et_name.getText());
        String description = String.valueOf(et_description.getText());
        String image = String.valueOf(et_image.getText());
        String result = isValid(name, description, image);

        clearFocus();
        switch (result) {
            case "valid":
                // ***************** add code to insert new data into recipe *****************
                break;
            case "empty name":
                emptyName();
                return;
            case "empty description":
                emptyDescription();
                return;
            case "empty image":
                emptyImage();
                return;
        }
    }

    public static String isValid(String name, String description, String image) {
        if (name.isEmpty()) {
            return "empty name";
        }
        else if (description.isEmpty()) {
            return "empty description";
        }
        else if (image.isEmpty()) {
            return "empty image";
        }
        return "valid";
    }

    /**
     * Function Description: clears highlight/focus on all edit texts
     */
    public void clearFocus() {
        et_name.clearFocus();
        et_description.clearFocus();
        et_image.clearFocus();
        et_name.setSelectAllOnFocus(false);
        et_description.setSelectAllOnFocus(false);
        et_image.setSelectAllOnFocus(false);
    }

    /**
     * Sets the focus and highlights name input text
     */
    public void emptyName() {
        et_name.setSelectAllOnFocus(true);
        et_name.requestFocus();
        Toast.makeText(EditFoodActivity.this, "Empty Name!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the focus and highlights description input text
     */
    public void emptyDescription() {
        et_description.setSelectAllOnFocus(true);
        et_description.requestFocus();
        Toast.makeText(EditFoodActivity.this, "Empty Description!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets the focus and highlights image input text
     */
    public void emptyImage() {
        et_image.setSelectAllOnFocus(true);
        et_image.requestFocus();
        Toast.makeText(EditFoodActivity.this, "Empty Image!", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
//        Intent x = new Intent(this, ProfilePage.class);
//        startActivity(x);
        finish();
    }
}