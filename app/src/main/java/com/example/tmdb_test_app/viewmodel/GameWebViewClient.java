package com.example.tmdb_test_app.viewmodel;

import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GameWebViewClient extends WebViewClient {

    @Override
    public void onPageFinished(WebView view, String url) {
        view.evaluateJavascript("javascript:set_player_name('Rajat')", null);
        triggerEventToAd("ready", view);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//        Toast.makeText(getActivity(), "WebView error: "+error.getDescription(), Toast.LENGTH_LONG).show();
        Log.e(getClass().getSimpleName(), "WebView error: "+error.getDescription());
    }


    private void triggerEventToAd(String event, WebView wv) {
        Log.e(getClass().getSimpleName(), "Triggering event from App to Ad: "+ event);
        wv.evaluateJavascript("javascript:triggeredEventFromApp('" + event + "')", null);
    }

}
