package com.lrl.app.presenter.contract;


import com.lrl.app.baen.BaseBean;
import com.lrl.app.baen.LoginBean;
import com.lrl.app.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/7/19.
 */

public interface LoginContract {

    interface ILoginModel {
        Observable<BaseBean<LoginBean>> login(String username, String password);
    }

    interface View extends BaseView {
        void saveInfo(LoginBean loginBean);
    }
}
