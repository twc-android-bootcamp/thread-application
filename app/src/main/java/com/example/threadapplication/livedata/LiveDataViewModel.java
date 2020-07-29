package com.example.threadapplication.livedata;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<Boolean> isStarted;
    private MutableLiveData<Long> timeTick;
    private Disposable disposable;

    public LiveDataViewModel() {
        isStarted = new MutableLiveData<>();
        isStarted.postValue(false);

        timeTick = new MutableLiveData<>();
        timeTick.postValue(0L);
    }

    public void observeTimeTick(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        timeTick.observe(owner, observer);
    }

    public void observeIsStarted(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        isStarted.observe(owner, observer);
    }

    public void setIsStarted(Boolean value) {
        this.isStarted.postValue(value);
        updateTimeTick(value);
    }

    private void updateTimeTick(Boolean value) {
        if(value) {
            startUpdateTimeTick();
        } else {
            stopUpdateTimeTick();
        }


    }

    private void stopUpdateTimeTick() {
        if(disposable == null) {
            return;
        }

        if(disposable.isDisposed()) {
            return;
        }

        disposable.dispose();
    }



    private void startUpdateTimeTick() {
        disposable = Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d("TimeTick", aLong.toString());
                Log.d("LiveData thread", Thread.currentThread().getName());
                Long currentValue = timeTick.getValue();
                timeTick.postValue(++currentValue);
            }
        });

    }

    @Override
    protected void onCleared() {
        stopUpdateTimeTick();
        super.onCleared();
    }
}
