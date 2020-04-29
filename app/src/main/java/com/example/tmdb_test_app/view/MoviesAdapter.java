package com.example.tmdb_test_app.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_test_app.R;
import com.example.tmdb_test_app.databinding.ItemMovieBinding;
import com.example.tmdb_test_app.model.Genre;
import com.example.tmdb_test_app.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private List<Genre> all_genres;

//    public MoviesAdapter(List<Movie> movies, List<Genre> all_genres) {
//        this.movies = movies;
//        this.all_genres = all_genres;
//    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                    R.layout.item_movie, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currMovie = movies.get(position);
        holder.itemMovieBinding.setModel(currMovie);
    }

    @Override
    public int getItemCount() {
        if (movies != null)
            return movies.size();
        else
            return 1;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding itemMovieBinding;

        public MovieViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.itemMovieBinding = binding;
        }

//        public void bind(Movie movie) {
//        }

//        @Override
//        public void onBindViewHolder(final MovieViewHolder holder, final int position) {
//
//        }
    }

}
