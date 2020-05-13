package com.example.tmdb_test_app.viewmodel;

import android.graphics.Point;

public interface PlayableAdInterface {
    void close();

    void open(String URL);

    Point getScreenSize();

    int getOrientation();

    void registerClick();

    void registerReplay();

    void toast(String toast); // Test function
}
