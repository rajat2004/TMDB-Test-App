package com.example.tmdb_test_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_test_app.R;
import com.example.tmdb_test_app.databinding.ActivityMainBinding;
import com.example.tmdb_test_app.model.Movie;
import com.example.tmdb_test_app.viewmodel.GameInterface;
import com.example.tmdb_test_app.viewmodel.GameWebViewClient;
import com.example.tmdb_test_app.viewmodel.MovieListViewModel;
import com.example.tmdb_test_app.viewmodel.PlayableAdInterface;

import java.util.List;

import static com.example.tmdb_test_app.utils.Constants.GAME_PATH;

public class MainActivity extends AppCompatActivity implements PlayableAdInterface {
    private final String TAG = getClass().getSimpleName();

    private MovieListViewModel model;
    private MoviesAdapter moviesAdapter;

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind recycler view
        RecyclerView recyclerView = activityMainBinding.moviesList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add WebView to display game
        wv = (WebView) findViewById(R.id.webView);
        initWebView();
        wv.loadUrl(GAME_PATH);

        model = new ViewModelProvider(this).get(MovieListViewModel.class);
        moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);

        Log.e(TAG, "Inside onCreate");

        getAllMovies();
    }

    @Override
    public void onDestroy() {
        close();
        wv = null;
        super.onDestroy();
    }

    public void loadAd(View view) {
        Intent intent = new Intent(this, AdActivity.class);
        startActivity(intent);
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

    private void initWebView() {
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.setWebViewClient(new GameWebViewClient());
        wv.addJavascriptInterface(new GameInterface(this), "Android");
    }

    // PlayableAdInterface
    public void close() {
        ViewGroup vg = (ViewGroup)findViewById(R.id.popuplayout);
        vg.removeAllViews();
        wv.destroy();
    }

    // TODO: Fix this
    public void open(String URL) {
        // Open in Browser
        wv.loadUrl(URL);
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}

