package com.lrl.app.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHP on 2017/7/25.
 */

public class RxCountDown {

    private Disposable disposable;

    /**
     * 倒计时用
     * @param time
     * @param show
     */
    public  void startCountDown(int time, final ShowCountDown show){
        if(time < 0){
            time = 0;
        }
        final int currentTime = time;
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return currentTime - aLong.intValue();
                    }
                })
                .take(currentTime + 1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        show.show(integer);
                    }
                });

    }



    public  void stop(){
        if(null != disposable && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public interface ShowCountDown{
        void show(int i);
    }
}
