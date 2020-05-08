package com.example.tmdb_test_app.viewmodel;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tmdb_test_app.R;

public class GameWebViewClient extends WebViewClient {

    Activity adActivity;
    private AdEventInterface adEventInterface = new AdEventInterface();

    public GameWebViewClient(Activity activity) {
        adActivity = activity;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        view.evaluateJavascript("javascript:set_player_name('Rajat')", null);
        adEventInterface.triggerEventToAd(view, AdEventInterface.EventType.READY, null);

        // Hide logo image since ad has finished loading
        adActivity.findViewById(R.id.loading).setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.e(getClass().getSimpleName(), "WebView error: "+error.getDescription());
    }

}
