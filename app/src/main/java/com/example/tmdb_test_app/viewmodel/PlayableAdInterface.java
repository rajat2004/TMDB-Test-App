package com.example.tmdb_test_app.viewmodel;

import android.graphics.Point;
import android.util.DisplayMetrics;

public interface PlayableAdInterface {
    void close();

    void open(String URL);

    Point getScreenSize();

    int getOrientation();

    // Common method to handle events triggered by Ad
    void eventFromAd(String event);

    void toast(String toast); // Test function
}
