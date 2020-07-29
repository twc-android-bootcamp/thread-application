package com.example.threadapplication.livedata;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<Boolean> isStarted;
    private MutableLiveData<Long> timeTick;
    private Disposable disposable;

    public MutableLiveData<Boolean> getIsStarted() {
        if(isStarted == null) {
            isStarted = new MutableLiveData<>();
            isStarted.postValue(false);
        }
        return isStarted;
    }

    public void setIsStarted(Boolean value) {
        this.isStarted.postValue(value);
        updateObservable(value);
    }

    private void updateObservable(Boolean value) {
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
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d("timeTick", aLong.toString());
                Long currentValue = timeTick.getValue();
                timeTick.postValue(++currentValue);
            }
        });

    }

    public MutableLiveData<Long> getTimeTick() {
        if(timeTick == null) {
            timeTick = new MutableLiveData<>();
            timeTick.postValue(0L);
        }
        return timeTick;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        stopUpdateTimeTick();
    }
}
