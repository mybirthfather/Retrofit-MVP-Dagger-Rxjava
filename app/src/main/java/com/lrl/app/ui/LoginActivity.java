package com.lrl.app.ui;

import com.lrl.app.baen.LoginBean;
import com.lrl.app.di.module.LoginModule;
import com.lrl.app.di.module.component.AppComponent;
import com.lrl.app.di.module.component.DaggerLoginComponent;
import com.lrl.app.presenter.LoginPresenter;
import com.lrl.app.presenter.contract.LoginContract;

public class LoginActivity  extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @Override
    public void saveInfo(LoginBean loginBean) {

    }

    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    public void init() {

    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build().inject(this);


    }
}
