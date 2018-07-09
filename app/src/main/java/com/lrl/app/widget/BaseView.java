package com.lrl.app.widget;

/**
 * Created by ZHP on 2017/6/24.
 */

public interface BaseView {


    void  showLoading();
    void  showError(String msg);
    void  dismissLoading();

}
