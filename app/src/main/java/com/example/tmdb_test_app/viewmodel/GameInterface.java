package com.example.tmdb_test_app.viewmodel;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;

import android.os.Handler;

import androidx.annotation.IntDef;

public class GameInterface {

    private final Handler mHandler;

    // Supported methods in interface
    @IntDef({
            Type.CLOSE,
            Type.OPEN,
            Type.TOAST
    })

    private @interface Type {
        int CLOSE = 1;
        int OPEN = 2;

        int TOAST = 3; // Just for testing
    }

    public GameInterface(PlayableAdInterface playableAdInterface) {
        this.mHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.arg1) {
                    case Type.CLOSE:
                        playableAdInterface.close();
                        break;

                    case Type.OPEN:
                        playableAdInterface.open((String) msg.obj);
                        break;

                    case Type.TOAST:
                        playableAdInterface.toast((String) msg.obj);
                        break;
                }
            }
        };

    }

    // Close the webview
    @JavascriptInterface
    public void close() {
        Log.e(getClass().getSimpleName(), "close called");
        Message msg = Message.obtain();
        msg.arg1 = Type.CLOSE;
        msg.obj = new Object();
        mHandler.handleMessage(msg);
    }

    @JavascriptInterface
    public void open(String URL) {
        Log.e(getClass().getSimpleName(), "open called: " + URL);
        Message msg = Message.obtain();
        msg.arg1 = Type.OPEN;
        msg.obj = URL;
        mHandler.handleMessage(msg);
    }

    // Show message from JS
    @JavascriptInterface
    public void showToast(String toast) {
        Log.e(getClass().getSimpleName(), "showToast called");
        Message msg = Message.obtain();
        msg.arg1 = Type.TOAST;
        msg.obj = toast;
        mHandler.sendMessage(msg);
    }
}
