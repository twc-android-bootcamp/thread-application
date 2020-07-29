package com.example.threadapplication.handler;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class LooperThread extends Thread {
    public Handler handler;
    public Looper looper;

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new Handler();
        Looper.loop();
    }
}
