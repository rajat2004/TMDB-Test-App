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
import com.example.tmdb_test_app.viewmodel.GameInterface;
import com.example.tmdb_test_app.viewmodel.GameWebViewClient;
import com.example.tmdb_test_app.viewmodel.PlayableAdInterface;

import static com.example.tmdb_test_app.utils.Constants.GAME_PATH;

public class AdActivity extends AppCompatActivity implements PlayableAdInterface {

    private WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_popup);

        // Add WebView to display game
        wv = (WebView) findViewById(R.id.webView);
        initWebView();
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

        String event = "orientation";
        Log.e(getClass().getSimpleName(), "Triggering event from App to Ad: "+ event);
        wv.evaluateJavascript("javascript:triggeredEventFromApp('" + event + "')", null);
    }



    // PlayableAdInterface
    public void close() {
        finish();
    }

    public void open(String URL) {
        // Open in Browser
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(browserIntent);
    }

    public Point getScreenSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }

    public int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    public void eventFromAd(String event) {
        // Handle events triggered by Ad
        // TODO: Add some events
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }



    private void initWebView() {
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.setWebViewClient(new GameWebViewClient());
        wv.addJavascriptInterface(new GameInterface(this), "Android");
    }
}
