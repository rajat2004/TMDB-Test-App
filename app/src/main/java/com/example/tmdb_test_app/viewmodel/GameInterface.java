package com.example.tmdb_test_app.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;

import androidx.annotation.IntDef;

public class GameInterface {

    private final Handler mHandler;
    private final String class_name = getClass().getSimpleName();

    // Supported methods in interface
    @IntDef({
            Type.CLOSE,
            Type.OPEN,
            Type.SCREEN_SIZE,
            Type.ORIENTATION,
            Type.CLICK,
            Type.REPLAY,

            Type.TOAST,
    })

    private @interface Type {
        int CLOSE = 1;
        int OPEN = 2;
        int SCREEN_SIZE = 3;
        int ORIENTATION = 4;
        int CLICK = 5;
        int REPLAY = 6;

        int TOAST = 99; // Just for testing
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

                    case Type.SCREEN_SIZE:
                        msg.obj = playableAdInterface.getScreenSize();
                        break;

                    case Type.ORIENTATION:
                        msg.obj = playableAdInterface.getOrientation();
                        break;

                    case Type.CLICK:
                        playableAdInterface.registerClick();
                        break;

                    case Type.REPLAY:
                        playableAdInterface.registerReplay();
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
        Log.e(class_name, "close called");
        Message msg = Message.obtain();
        msg.arg1 = Type.CLOSE;
        msg.obj = new Object();
        mHandler.handleMessage(msg);
    }

    @JavascriptInterface
    public void open(String URL) {
        Log.e(class_name, "open called: " + URL);
        Message msg = Message.obtain();
        msg.arg1 = Type.OPEN;
        msg.obj = URL;
        mHandler.handleMessage(msg);
    }

    @JavascriptInterface
    public String getScreenSize() {
        Log.e(class_name, "getScreenSize called");
        Message msg = Message.obtain();
        msg.arg1 = Type.SCREEN_SIZE;
        mHandler.handleMessage(msg);
//        Log.e(class_name, msg.obj.toString());
        Log.e(class_name, (String) msg.obj );
        return (String) msg.obj;
    }

    @JavascriptInterface
    public int getOrientation() {
        Log.e(class_name, "GetOrientation called");
        Message msg = Message.obtain();
        msg.arg1 = Type.ORIENTATION;
        mHandler.handleMessage(msg);
        Log.e(class_name, msg.obj.toString());
        return (Integer) msg.obj;
    }

    @JavascriptInterface
    public void registerClick() {
        Log.e(class_name, "RegisterClick called");
        Message msg = Message.obtain();
        msg.arg1 = Type.CLICK;
        mHandler.handleMessage(msg);
    }

    @JavascriptInterface
    public void registerReplay() {
        Log.e(class_name, "registerReplay called");
        Message msg = Message.obtain();
        msg.arg1 = Type.REPLAY;
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
