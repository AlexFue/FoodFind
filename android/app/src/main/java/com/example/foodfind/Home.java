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


import org.w3c.dom.Text;


public class Home extends AppCompatActivity  {
    public Button profileBtn, addNewRecipeBtn, logoutBtn, viewSavedBtn;
    public int userId1;
    private API api;
    public TextView homeRecipe1;
    private Recipe userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_feed);

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
                    //trying to pass the userId here
                    startActivity(i);
                }
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://myawesomefoodfindapp.herokuapp.com/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        api = retrofit.create(API.class);
//
//        getUserFoodList();

//        viewSavedFoodsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (view.getId() == R.id.viewSavedFoodsBtn) {
//                    Intent i = new Intent(view.getContext(), SavedFoodsActivity.class);
//                    startActivity(i);
//                }
//            }
//        });


    }

//    private void getUserFoodList() {
//        userId1 = 1;
//        Call<FoodList> call = api.getFoodListByUserId(userId);
//        call.enqueue(new Callback<FoodList>() {
//            @Override
//            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
//                if(!response.isSuccessful()){
//                    System.out.println(response.code());
//                    return;
//                }
//                FoodList foodlist = response.body();
//                List<Recipe> recipes = foodlist.getRecipes();
//
//                for(Recipe recipe: recipes) {
//                    recipe.getName();
//                    recipe.getDescription();
//                    recipe.getImage();
////                    if (i++ == recipes.size()) {
////                        Intent a = new Intent(Home.this, ProfilePage.class);
////                        //a.putExtra("name", recipe.getName());
////                        startActivity(a);
////
////                    }
//                }
//
////                homeRecipe1 = (TextView) findViewById(R.id.homeRecipe1);
////                homeRecipe1.setText(recipe);
//                //Log.d("Home recipe 1", response.body().toString());
//                //homeRecipe1.setText(response.body().toString());
//
//
//                Intent i = new Intent(Home.this, ProfilePage.class);
//                //i.putExtra("name",(Recipe) recipe.getName());
//                startActivity(i);
//
//            }
//
//            @Override
//            public void onFailure(Call<FoodList> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });
//    }

//    private void getInstagramData() {
//        // below line is use to initialize the variable for our request queue.
//        mRequestQueue = Volley.newRequestQueue(Home.this);
//
//        // below line is use to clear cache this will
//        // be use when our data is being updated.
//        mRequestQueue.getCache().clear();
//
//        // below is the url for getting data
//        // from API in json format.
//        String url = "https://myawesomefoodfindapp.herokuapp.com/api/view_recipes";
//
//        // below line we are  creating a new request queue.
//        RequestQueue queue = Volley.newRequestQueue(Home.this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                progressBar.setVisibility(View.GONE);
//                try {
//                    JSONArray dataArray = response.getJSONArray("data");
//                    for (int i = 0; i < dataArray.length(); i++) {
//                        // below line is to extract data from JSON file.
//
//                        //Need to return recipe name, image and description
//                        JSONObject dataObj = dataArray.getJSONObject(i);
//                        String id = dataObj.getString("userId");
//                        String media_type = dataObj.getString("media_type");
//                        String permalink = dataObj.getString("permalink");
//                        String media_url = dataObj.getString("media_url");
//                        String username = dataObj.getString("username");
//                        String caption = dataObj.getString("caption");
//                        String timestamp = dataObj.getString("timestamp");
//
//                        // below line is to add a constant author image URL to our recycler view.
//                        String author_url = "https://instagram.fnag5-1.fna.fbcdn.net/v/t51.2885-19/s320x320/75595203_828043414317991_4596848371003555840_n.jpg?_nc_ht=instagram.fnag5-1.fna.fbcdn.net&_nc_ohc=WzA_n4sdoQIAX9B5HWJ&tp=1&oh=05546141f5e40a8f02525b497745a3f2&oe=6031653B";
//                        int likesCount = 100 + (i * 10);
//
//                        // below line is use to add data to our modal class.
//                        InstaModal instaModal = new InstaModal(id, media_type, permalink, media_url, username, caption, timestamp, author_url, likesCount);
//
//                        // below line is use to add modal
//                        // class to our array list.
//                        instaModalArrayList.add(instaModal);
//
//                        // below line we are creating an adapter class and adding our array list in it.
//                        InstagramFeedRVAdapter adapter = new InstagramFeedRVAdapter(instaModalArrayList, Home.this);
//                        RecyclerView instRV = findViewById(R.id.idRVInstaFeeds);
//
//                        // below line is for setting linear layout manager to our recycler view.
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Home.this, RecyclerView.VERTICAL, false);
//
//                        // below line is to set layout manager to our recycler view.
//                        instRV.setLayoutManager(linearLayoutManager);
//
//                        // below line is to set adapter
//                        // to our recycler view.
//                        instRV.setAdapter(adapter);
//                    }
//                } catch (JSONException e) {
//                    // handling error case.
//                    e.printStackTrace();
//                    Toast.makeText(Home.this, "Fail to get Data.." + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // handling error message.
//                Toast.makeText(Home.this, "Fail to get Data.." + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonObjectRequest);
//    }
}
