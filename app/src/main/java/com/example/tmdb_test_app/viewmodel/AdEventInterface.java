package com.example.tmdb_test_app.viewmodel;

import android.util.Log;
import android.webkit.WebView;

public class AdEventInterface {

    // Define events

    public void triggerEventToAd(String event, WebView wv) {
        Log.e(getClass().getSimpleName(), "Triggering event from App to Ad: "+ event);
        wv.evaluateJavascript("javascript:triggeredEventFromApp('" + event + "')", null);
    }

}
