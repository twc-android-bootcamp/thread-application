package com.example.threadapplication.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyTimer {
    private Disposable disposable;

    public void stop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void start(Consumer<Long> callback) {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(11)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }
}
