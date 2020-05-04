package com.example.tmdb_test_app.viewmodel;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class myWebViewClient extends WebViewClient{

    @Override
    public void onPageFinished(WebView view, String url) {
        view.evaluateJavascript("javascript:set_player_name('Rajat')", null);
    }

}
