package com.lrl.app.presenter.contract;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.lrl.app.widget.BaseView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


/**
 * Created by ZHP on 2017/6/24.
 */

public class BasePresenter<M,V extends BaseView> {
    List<Disposable> disposableList = new ArrayList<>();


    protected M mModel;

    protected V mView;


    protected Context mContext;



    public BasePresenter(M m, V v){

        this.mModel=m;
        this.mView = v;
        initContext();

    }

    public BasePresenter(V v){
        this.mView = v;
        initContext();
    }



    private void initContext(){
        disposableList.clear();
        if(mView instanceof Fragment){
            mContext = ((Fragment)mView).getActivity();
        }
        else if(mView instanceof Activity){
            mContext = (Activity) mView;
        }
    }

    /**
     * 和View脱离关系
     */
    public void detachView() {
        mView = null;
        disposable();
    }

    public boolean hasView(){
        return null != mView;
    }

    public void addDisposable(Disposable disposable){
        if(null != disposable){
            disposableList.add(disposable);
        }
    }

    public void disposable(){
        for (Disposable disposable : disposableList) {
            disposable.dispose();
        }
        disposableList.clear();
    }
}
