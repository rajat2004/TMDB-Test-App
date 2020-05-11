package com.example.tmdb_test_app.viewmodel;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.StringDef;

public class AdEventInterface {

    // Define events
    @StringDef({
            EventType.READY,
            EventType.PAUSE,
            EventType.RESUME,
            EventType.ORIENTATION,
    })

    public @interface EventType {
        String READY = "ready";
        String PAUSE = "pause";
        String RESUME = "resume";
        String ORIENTATION = "orien";
    }

    public void triggerEventToAd(WebView wv, String event, ValueCallback<String> callback) {
        Log.e(getClass().getSimpleName(), "Triggering event from App to Ad: "+ event);
        wv.evaluateJavascript("(function() { window.dispatchEvent(" + event + "); })();", callback);
    }

}
