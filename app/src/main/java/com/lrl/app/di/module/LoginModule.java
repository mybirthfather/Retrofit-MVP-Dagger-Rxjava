package com.lrl.app.di.module;


import com.lrl.app.http.ApiService;
import com.lrl.app.http.LoginModel;
import com.lrl.app.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2017/7/6.
 */
@Module
public class LoginModule {

    private LoginContract.View mView;

    public LoginModule(LoginContract.View view) {

        this.mView = view;
    }

    @Provides
    public LoginContract.View provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService) {
        return new LoginModel(apiService);
    }
}
