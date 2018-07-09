package com.lrl.app.http.net.rto_subscriber;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZHP on 2017/6/26.
 */


public  abstract  class DefualtSubscriber<T> implements Observer<T> {
    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    public Disposable getDisposable() {
        return disposable;
    }
}
