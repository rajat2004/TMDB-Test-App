package com.example.tmdb_test_app;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private List<Genre> all_genres;

    public MoviesAdapter(List<Movie> movies, List<Genre> all_genres) {
        this.movies = movies;
        this.all_genres = all_genres;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView release_date;
        TextView title;
        TextView rating;
        TextView genres;

        public MovieViewHolder(View itemView) {
            super(itemView);
            release_date = itemView.findViewById(R.id.item_movie_release_date);
            title = itemView.findViewById(R.id.item_movie_title);
            rating = itemView.findViewById(R.id.item_movie_rating);
            genres = itemView.findViewById(R.id.item_movie_genre);
        }

        public void bind(Movie movie) {
            release_date.setText(movie.getReleaseDate());
            title.setText(movie.getTitle());
            rating.setText((String.valueOf(movie.getRating())));
            genres.setText(getGenreStrings(movie.getGenreIds()));
        }

        private String getGenreStrings(List<Integer> genreIds) {
            List<String> movie_genres = new ArrayList<>();

            for(Integer id : genreIds) {
                for(Genre genre : all_genres) {
                    if(id == genre.getId()) {
                        movie_genres.add(genre.getName());
                        break;
                    }
                }
            }

            return TextUtils.join(", ", movie_genres);
        }
    }
}
