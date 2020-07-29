package com.example.threadapplication.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.threadapplication.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

public class RxJavaActivity extends AppCompatActivity {
    Button startBtn;
    Button stopBtn;
    MyTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        startBtn = (Button) findViewById(R.id.start);
        stopBtn = (Button) findViewById(R.id.stop);
        myTimer = new MyTimer();

        setOnListener();
    }

    private void setOnListener() {

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTimer.stop();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTimer.start(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startBtn.setText(aLong.toString());
                        Log.d("thread: ", Thread.currentThread().getName());
                    }
                });
            }
        });
    }
}