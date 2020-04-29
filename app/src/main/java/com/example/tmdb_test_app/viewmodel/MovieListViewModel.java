package com.example.tmdb_test_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//import com.example.tmdb_test_app.Movie;
import com.example.tmdb_test_app.model.Movie;
import com.example.tmdb_test_app.network.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> movies;

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = new MutableLiveData<>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {
        movies = MovieRepository.getInstance().getMovies();
    }
}
