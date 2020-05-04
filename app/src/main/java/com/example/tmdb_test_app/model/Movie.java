package com.example.tmdb_test_app.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.tmdb_test_app.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static com.example.tmdb_test_app.utils.Constants.IMAGE_BASE_URL;

public class Movie {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("release_date")
    public String release_date;

    @SerializedName("vote_average")
    public float rating;

    @SerializedName("genre_ids")
    public List<Integer> genre_ids;

//    @BindingAdapter({"poster_path"})
//    public static void loadImage(ImageView view, String path) {
//        Glide.with(view.getContext())
//                .load(IMAGE_BASE_URL + path)
////                .placeholder(R.drawable.)
//                .into(view);
//    }
}
