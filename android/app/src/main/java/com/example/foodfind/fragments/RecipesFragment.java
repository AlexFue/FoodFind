package com.example.foodfind.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodfind.R;
import com.example.foodfind.Recipe;
import com.example.foodfind.RecipeAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.parse.ParseQuery;



public class RecipesFragment extends Fragment {
    public static final String TAG = "RecipesFragment";
    private RecyclerView rvRecipes;
    protected RecipeAdapter adapter;
    protected List<Recipe> allRecipes;

    public RecipesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRecipes = view.findViewById(R.id.rvRecipes);

        allRecipes = new ArrayList<>();
        adapter = new RecipeAdapter(getContext(), allRecipes);


        // Steps to use the recycler view:
        // 1.) create layout for one row in the list
        // 2.) create the adapter
        // 3.) create a data source
        // 4.) set the adapter on the recycler view
        rvRecipes.setAdapter(adapter);
        // 5.) set the layout manager on the recycler view
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }


    protected void queryPosts() {
        ParseQuery<Recipe> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}

