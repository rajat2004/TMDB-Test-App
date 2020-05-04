package com.example.tmdb_test_app.network;

import com.example.tmdb_test_app.model.MoviesResponse;
import com.example.tmdb_test_app.model.GenresResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBService {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
