package com.example.tmdb_test_app.viewmodel;

import android.graphics.Point;
import android.util.DisplayMetrics;

public interface PlayableAdInterface {

    void close();

    void open(String URL);

    void toast(String oast); // Test function

    Point getScreenSize();

    int getOrientation();
}
