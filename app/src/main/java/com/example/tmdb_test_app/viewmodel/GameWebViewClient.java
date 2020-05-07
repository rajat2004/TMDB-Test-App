package com.example.tmdb_test_app.viewmodel;

import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameWebViewClient extends WebViewClient {

    private AdEventInterface adEventInterface = new AdEventInterface();

    @Override
    public void onPageFinished(WebView view, String url) {
        view.evaluateJavascript("javascript:set_player_name('Rajat')", null);
        Log.e(getClass().getSimpleName(), "Triggering ready event");
        adEventInterface.triggerEventToAd("ready", view);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.e(getClass().getSimpleName(), "WebView error: "+error.getDescription());
    }

}
