package com.example.tmdb_test_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_test_app.R;
import com.example.tmdb_test_app.databinding.ActivityMainBinding;
import com.example.tmdb_test_app.model.Movie;
import com.example.tmdb_test_app.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private MovieListViewModel model;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind recycler view
        RecyclerView recyclerView = activityMainBinding.moviesList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = new ViewModelProvider(this).get(MovieListViewModel.class);
        moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);

        Log.e(TAG, "Inside onCreate");

        getAllMovies();
    }


    private void getAllMovies() {
        model.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.e(TAG, "movies has changed");
                moviesAdapter.setMovies(movies);
            }
        });
    }

    public void loadAd(View view) {
        Log.e(getClass().getSimpleName(), "Launching Ad");
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
    }
}

