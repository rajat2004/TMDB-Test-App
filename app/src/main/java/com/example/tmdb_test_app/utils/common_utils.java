package com.example.tmdb_test_app.utils;

import android.graphics.Point;
import android.text.TextUtils;

import com.example.tmdb_test_app.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class common_utils {
    public String getGenreString(int id, List<Genre> all_genres) {
        for(Genre genre : all_genres) {
            if(id == genre.getId()) {
                return genre.getName();
            }
        }

        // If id not present...
        return "";
    }

    public String getGenreStrings(List<Integer> genreIds, List<Genre> all_genres) {
        List<String> movie_genres = new ArrayList<>();

        for(Integer id : genreIds) {
            movie_genres.add(getGenreString(id, all_genres));
        }

        return TextUtils.join(", ", movie_genres);
    }

    public static String shortString(Point p) {
        return "[" + p.x + "," + p.y + "]";
    }
}
