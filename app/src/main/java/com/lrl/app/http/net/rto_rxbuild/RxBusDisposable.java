package com.lrl.app.http.net.rto_rxbuild;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

/**
 * Created by jiabaofeng on 2018/1/17.
 */

public class RxBusDisposable {
    private Subject<Object> subject;
    private Disposable disposable;
    private HashMap<String,RxBusDisposable> busMap;
    private String tag;

    public Subject<Object> getSubject() {
        return subject;
    }

    public void setSubject(Subject<Object> subject) {
        this.subject = subject;
    }

    public void dispose() {
        if(null != disposable && !disposable.isDisposed()){
            disposable.dispose();
        }
        if(null != busMap && busMap.containsKey(tag)){
            busMap.remove(tag);
        }
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public HashMap<String, RxBusDisposable> getBusMap() {
        return busMap;
    }

    public void setBusMap(HashMap<String,RxBusDisposable> busMap) {
        this.busMap = busMap;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
