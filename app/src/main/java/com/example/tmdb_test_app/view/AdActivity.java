package com.example.tmdb_test_app.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tmdb_test_app.R;
import com.example.tmdb_test_app.viewmodel.AdEventInterface;
import com.example.tmdb_test_app.viewmodel.GameInterface;
import com.example.tmdb_test_app.viewmodel.GameWebViewClient;
import com.example.tmdb_test_app.viewmodel.MyTouchListener;
import com.example.tmdb_test_app.viewmodel.PlayableAdInterface;

import static com.example.tmdb_test_app.utils.Constants.GAME_PATH;

import com.example.tmdb_test_app.utils.common_utils;

public class AdActivity extends AppCompatActivity implements PlayableAdInterface {

    private final String class_name = getClass().getSimpleName();

    private WebView wv;
    private GameInterface gi;
    private AdEventInterface adEventInterface;

    // Some metrics
    long startTime;
    long totalTime = 0;
    int clicks = 0;
    int replays = 0;
    boolean url_opened = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_popup);

        // Add WebView to display game
        wv = (WebView) findViewById(R.id.webView);
        initWebView();

        startTime = System.nanoTime();

        wv.loadUrl(GAME_PATH);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

        adEventInterface.triggerEventToAd(wv, AdEventInterface.EventType.ORIENTATION, null);
    }

    @Override
    protected void onPause() {
        totalTime += (System.nanoTime() - startTime);
//        Log.e(class_name, "Pausing: " + totalTime);
        wv.pauseTimers();
        wv.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wv.resumeTimers();
        startTime = System.nanoTime();
        wv.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewGroup parent = (ViewGroup) wv.getParent();
        parent.removeView(wv);
        wv.destroy();
        wv = null;

        // Log some metrics
        // Don't use time here since onPause is called on finish() which updates the time
        // Log.e(class_name, "Time spent: " + totalTime/1e9);
        Log.e(class_name, "Clicks: " + clicks);
        Log.e(class_name, "URL opened: " + url_opened);
        Log.e(class_name, "Replays: " + replays);
    }



    // PlayableAdInterface
    public void close() {
        totalTime += System.nanoTime() - startTime;
        Log.e(class_name, "Total time spent: " + totalTime/1e9);
        finish();
    }

    public void open(String URL) {
        // Open in Browser
        url_opened = true;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }

    public String getScreenSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return common_utils.shortString(size);
    }

    public int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    public void registerClick() {
        clicks++;
    }

    public void registerReplay() {
        replays++;
    }


    private void initWebView() {
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.setWebViewClient(new GameWebViewClient(this));

        gi = new GameInterface(this);
        adEventInterface = new AdEventInterface();
        // Name of interface to be used in JS by Ad
        wv.addJavascriptInterface(gi, "HotstarAndroid");
        wv.setOnTouchListener(new MyTouchListener());
    }
}
