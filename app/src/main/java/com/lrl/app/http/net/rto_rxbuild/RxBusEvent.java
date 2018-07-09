package com.lrl.app.http.net.rto_rxbuild;


import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by ZHP on 2017/6/25.
 */

public class RxBusEvent {
    private HashMap<String,RxBusDisposable> busMap = new HashMap<>();

    private static volatile RxBusEvent defaultInstance;

    // 单例RxBus
    public static RxBusEvent getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBusEvent.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBusEvent();
                }
            }
        }
        return defaultInstance ;
    }

    private RxBusEvent() {

    }

    /**
     * 事件发送
     * @param tag   绑定事件标记
     * @param obj
     */
    public void post(String tag, Object obj) {
        if(busMap.containsKey(tag)){
            RxBusDisposable rxBusDisposable = busMap.get(tag);
            Subject<Object> mBus = rxBusDisposable.getSubject();
            mBus.onNext(obj);
        }else{
            try {
                throw new Exception("未找到注册的事件 来自tag: "+tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事件发送
     * @param tag   绑定事件标记
     */
    public void post(String tag) {
        post(tag," ");
    }

    /**
     * 订阅一个事件
     * @param tag 事件标记  再发送事件时使用
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> RxBusDisposable toObservable(String tag, Class<T> tClass, Consumer<T> consumer) {
        RxBusDisposable rxBusDisposable;
        Subject<Object> mBus;
        if(busMap.containsKey(tag)){
            rxBusDisposable = busMap.get(tag);
            mBus = rxBusDisposable.getSubject();
        }else{
            rxBusDisposable = new RxBusDisposable();
            mBus = PublishSubject.create().toSerialized();
            rxBusDisposable.setSubject(mBus);
            busMap.put(tag,rxBusDisposable);
        }

        Disposable disposable =  mBus.ofType(tClass).subscribe(consumer);
        rxBusDisposable.setBusMap(busMap);
        rxBusDisposable.setDisposable(disposable);
        rxBusDisposable.setTag(tag);
        return rxBusDisposable;
    }

    /**
     * 再onDestory移除事件订阅
     * @param tag   注册填写的Tag
     */
    public void dispose(String tag) {
        if(busMap.containsKey(tag)){
            RxBusDisposable rxBusDisposable = busMap.get(tag);
            rxBusDisposable.dispose();
        }
    }

}
