package com.example.tmdb_test_app.view;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.tmdb_test_app.R;
import com.example.tmdb_test_app.databinding.ActivityMainBinding;

public class AdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(R.layout.activity_ad);

        // Get Intent that started this activity
        Intent intent = getIntent();
    }
}
