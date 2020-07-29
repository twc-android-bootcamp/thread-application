package com.example.threadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.threadapplication.handler.HandlerMessageActivity;
import com.example.threadapplication.livedata.LiveDataActivity;
import com.example.threadapplication.rxjava.RxJavaActivity;

public class MainActivity extends AppCompatActivity {
    private Button goThreadBtn;
    private Button goHandlerBtn;
    private Button goRxJavaBtn;
    private Button goLiveDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goThreadBtn = (Button) findViewById(R.id.go_thread);
        goHandlerBtn = (Button) findViewById(R.id.go_handler_btn);
        goRxJavaBtn = (Button) findViewById(R.id.go_rx_java_btn);
        goLiveDataBtn = (Button) findViewById(R.id.go_live_data_btn);

        setListener();

    }

    private void setListener() {

        goThreadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
            }
        });

        goHandlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HandlerMessageActivity.class);
                startActivity(intent);
            }
        });

        goRxJavaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent);
            }
        });

        goLiveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LiveDataActivity.class);
                startActivity(intent);
            }
        });
    }

}