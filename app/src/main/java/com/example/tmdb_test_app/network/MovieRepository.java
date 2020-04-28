package com.example.tmdb_test_app.network;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmdb_test_app.BuildConfig;
//import com.example.tmdb_test_app.Genre;
//import com.example.tmdb_test_app.Movie;
//import com.example.tmdb_test_app.MoviesResponse;
import com.example.tmdb_test_app.model.Genre;
import com.example.tmdb_test_app.model.GenresResponse;
import com.example.tmdb_test_app.model.Movie;
import com.example.tmdb_test_app.model.MoviesResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String LANGUAGE = "en_US";

    private static MovieRepository movieRepository;
    private TMDBService service;

    private final String TAG = getClass().getSimpleName();

    private MovieRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        service = retrofit.create(TMDBService.class);
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();

        service.getPopularMovies(BuildConfig.TMDB_API_KEY, LANGUAGE, 1)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if(moviesResponse != null && moviesResponse.getMovies() != null) {
                                Log.e(TAG, "movieResponse successful");
                                data.setValue(moviesResponse.getMovies());
                            } else {
                                Log.e(TAG, "moviesResponse empty");
                            }
                        } else {
                            Log.e(TAG, "Response not successful");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                        Log.e(TAG, "getMovies failure");
                    }
                });

        return data;
    }

    public LiveData<List<Genre>> getGenres() {
        MutableLiveData<List<Genre>> data = new MutableLiveData<>();

        service.getGenres(BuildConfig.TMDB_API_KEY, LANGUAGE)
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GenresResponse> call, @NonNull Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                data.setValue(genresResponse.getGenres());
                            } else {
                                Log.e(TAG, "genresResponse empty");
                            }
                        } else {
                            Log.e(TAG, "GenreResponse not successful");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GenresResponse> call, @NonNull Throwable t) {
                        Log.e(TAG, "getGenres Failure");
                    }
                });

        return data;
    }
}
