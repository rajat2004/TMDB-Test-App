package com.example.tmdb_test_app.viewmodel;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyTouchListener implements View.OnTouchListener {

    private final String class_name = getClass().getSimpleName();

    private long m_DownTime = 0;
    private int clicks = 0;
    private int btn_clicks = 0;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                m_DownTime = event.getEventTime();
                Log.e(class_name, "Down Action");
                break;

            case MotionEvent.ACTION_UP:
                Log.e(class_name, "Up action");
                Log.e(class_name, "Duration: " + (event.getEventTime() - m_DownTime));
                clicks+=1;
                Log.e(class_name, "Clicks: " + clicks);
                break;

            default:
                break;
        }

        return false;
    }
}
