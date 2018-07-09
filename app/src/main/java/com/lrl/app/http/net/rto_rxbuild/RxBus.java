package com.lrl.app.http.net.rto_rxbuild;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by ZHP on 2017/6/25.
 */

public class RxBus {

    private static volatile RxBus defaultInstance;

//    private final Subject<Object, Object> bus;
//    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
//    public RxBus() {
//        bus = new SerializedSubject<>(PublishSubject.create());
//    }
    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance ;
    }

//    // 发送一个新的事件
//    public void post (Object o) {
//        bus.onNext(o);
//    }
//    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//    public <T> Observable<T> toObservable (Class<T> eventType) {
//        return bus.ofType(eventType);
//
//    }


    private final Subject<Object> mBus;

    private RxBus() {
        // toSerialized method made bus thread safe
        mBus = PublishSubject.create().toSerialized();
    }



    public void post(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }
}
