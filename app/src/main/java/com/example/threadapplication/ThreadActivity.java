package com.example.threadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThreadActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        button = (Button) findViewById(R.id.start);
        setListener();
    }

    private void setListener() {

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                button.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Runnable runnable = new MyRunnable();
                        for (int i = 0; i < 10; i++) {
                            Log.i("sleep Thread", Thread.currentThread().getName());
                            SystemClock.sleep(1000);
                            final int count = i;
                            button.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("render Thread", Thread.currentThread().getName());
                                    button.setText("v:" + count);
                                }

                            });
                        }

                    }
                }).start();
            }
        });
    }

    private class MyRunnable implements Runnable {
        int i = 0;

        @Override
        public void run() {
            i++;
        }
    }
}