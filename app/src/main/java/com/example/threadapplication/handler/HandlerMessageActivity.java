package com.example.threadapplication.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.threadapplication.R;

import androidx.appcompat.app.AppCompatActivity;

public class HandlerMessageActivity extends AppCompatActivity {

    private Button buttonTaskA;
    private Button buttonTaskB;
    private Button buttonStart;
    private Button buttonStop;
    private LooperThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_message);

        buttonTaskA = (Button) findViewById(R.id.start_task_a);
        buttonTaskB = (Button) findViewById(R.id.start_task_b);
        buttonStart = (Button) findViewById(R.id.start_thread);
        buttonStop = (Button) findViewById(R.id.stop_thread);

        thread = new LooperThread();
        setClickListener();
    }

    private void setClickListener() {

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.looper.quit();
            }
        });


        buttonTaskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<5; i++) {
                            Log.d("debug", "i:" + i);
                            final int num = i;
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast toast = Toast.makeText(getApplicationContext(), "test" + num, Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });

                            SystemClock.sleep(1000);
                        }
                    }
                });
            }
        });

        buttonTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<5; i++) {
                            Log.d("debug", "i:" + i);
                            SystemClock.sleep(500);
                        }
                    }
                });
            }
        });
    }
}
