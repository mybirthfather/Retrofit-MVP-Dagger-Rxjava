package com.lrl.app.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHP on 2017/7/25.
 */

public class RxRequestRepeat {

    private Disposable disposable;

    private String mTime;

    public void setmTime(String time) {
        this.mTime = time;
    }

    /**
     * 发延时消息
     *
     * @param show
     */

    public void postMessage(final ShowCountDown show) {
        if (null != mTime && Integer.valueOf(mTime) != -1) {
            disposable = Observable.interval(Integer.valueOf(mTime), 5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            show.show();
                        }
                    });
        }
    }

    public void stop() {
        if (null != disposable) {
            disposable.dispose();
        }
        disposable = null;
    }

    public interface ShowCountDown {
        void show();
    }

    public boolean getStaus() {
        if (null != disposable) {
            return disposable.isDisposed();
        } else {
            return true;
        }

    }

}
