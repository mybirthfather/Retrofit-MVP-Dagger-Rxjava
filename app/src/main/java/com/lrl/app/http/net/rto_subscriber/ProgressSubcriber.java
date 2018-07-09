package com.lrl.app.http.net.rto_subscriber;

import android.content.Context;

import com.lrl.app.http.net.rto_exception.ApiException;
import com.lrl.app.http.net.rto_exception.BaseException;
import com.lrl.app.utils.LogUtil;
import com.lrl.app.widget.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * Created by ZHP on 2017/6/25.
 */

public abstract class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T> {
    private BaseView mView;


    public ProgressSubcriber(Context context, BaseView view) {
        super(context);
        this.mView = view;

    }


    public boolean isShowProgress() {
        return true;
    }


    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        if (isShowProgress() && null!=mView) {
            mView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        if(null != mView){
            mView.dismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {
            ApiException a = (ApiException) e;
            LogUtil.d("api", "------" + a.getDisplayMessage() + "------" + a.getCode());
            if (a.getCode() == BaseException.ERROR_TOKEN) {
                toLogin();
//                UserinfoUtil.loginOut(mContext);
            }
            if(null!=mView){
                mView.showError(a.getDisplayMessage());
            }
        }else{
//            if(e instanceof SocketTimeoutException){
//                ApiException exception = new ApiException(500,"请求超时，请重试");
//                if(null!=mView){
//                    mView.showError(exception.getDisplayMessage());
//                }
//            }
//            else{
//                ApiException exception = new ApiException(500,"出错了，请重试");
//                if(null!=mView){
//                    mView.showError(exception.getDisplayMessage());
//                }
//            }
        }
        if (isShowProgress()) {
            if(null!=mView){
                mView.dismissLoading();
            }
        }

    }

    private void toLogin() {
//        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }


}
