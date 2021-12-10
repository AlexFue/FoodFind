package com.example.foodfind;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.foodfind.API;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import org.w3c.dom.Text;


public class Home extends AppCompatActivity   {
    private int userId;
    private API api;
    private TextView viewUserId;
    private TextView viewRecipe1, viewRecipe2, viewRecipe3, viewRecipe4;
    private TextView viewUserFoodList;
    private Button homeButton;
    private Button addRecipeButton;
    private ImageView showRecipeImage;
    private String image;
    private Button removeRecipeButton;
    public Button profileBtn, addNewRecipeBtn, logoutBtn, viewSavedFoodsBtn;
    private Button addButton;
    public final static String EXTRA_MESSAGE = "com.example.foodfind.Home";

    private static final String TAG = "Home";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_feed);

        if(getIntent().hasExtra("select")) {
            Home thing = getIntent().getParcelableExtra("select");
            Log.d(TAG, "onCreate: "+ thing.toString());
        }
        Intent intent = getIntent();
        userId= intent.getIntExtra("UserId: ", userId);
        viewUserId = findViewById(R.id.viewUserId);
        viewRecipe1 = findViewById(R.id.homeRecipe1);
        viewRecipe2 = findViewById(R.id.homeRecipe2);
        viewRecipe3 = findViewById(R.id.homeRecipe3);
        viewRecipe4 = findViewById(R.id.homeRecipe4);
        viewUserFoodList = findViewById(R.id.viewUserFoodList);
        intent.putExtra("select", userId);

        profileBtn = findViewById(R.id.profileBtn);
        addNewRecipeBtn = findViewById(R.id.addNewRecipeBtn);

//        viewSavedFoodsBtn = findViewById(R.id.viewSavedFoodsBtn);



        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getUserFoodList();

                if(view.getId() == R.id.profileBtn){
//                    getUserFoodList();


                    Intent i = new Intent(view.getContext(), ProfilePage.class);
                    TextView homeRecipe1 = (TextView)  findViewById(R.id.homeRecipe1);
                    String message = homeRecipe1.getText().toString();
                    i.putExtra(EXTRA_MESSAGE, message);
                    startActivity(i);
                }
            }
        });
        addNewRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.addNewRecipeBtn){
                    Intent i = new Intent(view.getContext(), CreateNewRecipeActivity.class);
                    startActivity(i);
                }
            }
        });


       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();

       api = retrofit.create(API.class);

       getUserFoodList();

       viewSavedFoodsBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (view.getId() == R.id.viewSavedFoodsBtn) {
                   Intent i = new Intent(view.getContext(), SavedFoodsActivity.class);
                   startActivity(i);
               }
           }
       });


    }

    private void getUserFoodList() {
        userId = 1;
        Call<FoodList> call = api.getFoodListByUserId(userId);
        call.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                    return;
                }
                FoodList foodlist = response.body();
                viewUserId.setText(Integer.toString(userId));

                List<Recipe> recipes = foodlist.getRecipes();
                for (Recipe recipe: recipes){
                    if(recipe.getUserId() != userId ){
                        //show 1 of each
                        continue;
                    }
                    String content = "";
                    content += "User id" + recipe.getUserId() + "\n";
                    content += "Recipe name: " + recipe.getName() + "\n";
                    content += "description: " + recipe.getDescription() + "\n";
                    content += "image: " + recipe.getImage() + "\n\n";
                    viewUserFoodList.append(content);
                }



            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


}
