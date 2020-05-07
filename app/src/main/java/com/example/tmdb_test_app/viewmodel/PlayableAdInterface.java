package com.example.tmdb_test_app.viewmodel;

import android.util.DisplayMetrics;

public interface PlayableAdInterface {

    void close();

    void open(String URL);

    void toast(String oast); // Test function

    DisplayMetrics getScreenSize();
}
