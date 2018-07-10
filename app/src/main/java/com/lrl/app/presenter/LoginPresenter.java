package com.lrl.app.presenter;


import com.lrl.app.baen.LoginBean;
import com.lrl.app.http.net.rto_rxbuild.RxHttpReponseCompat;
import com.lrl.app.http.net.rto_subscriber.ProgressSubcriber;
import com.lrl.app.presenter.contract.BasePresenter;
import com.lrl.app.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.View> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.View view) {
        super(iLoginModel, view);
    }

    public void login(String username, String password) {
        mModel.login(username, password).compose(RxHttpReponseCompat.<LoginBean>compatResult())
                .subscribe(new ProgressSubcriber<LoginBean>(mContext, mView) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if(null != mView)mView.saveInfo(loginBean);
                    }
                });

    }
}
