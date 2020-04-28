package com.example.tmdb_test_app;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie> movies);

    void onError();
}
