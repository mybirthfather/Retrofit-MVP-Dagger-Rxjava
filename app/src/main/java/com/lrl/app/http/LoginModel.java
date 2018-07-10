package com.lrl.app.http;


import com.lrl.app.baen.BaseBean;
import com.lrl.app.baen.LoginBean;
import com.lrl.app.presenter.contract.LoginContract;
import com.lrl.app.utils.Constant;
import com.lrl.app.utils.MD5;
import com.lrl.app.utils.ParmaUtil;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/7/19.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String username, String password) {
        String mD5password = password;
        try {
            mD5password = MD5.md5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TreeMap<String, String> parma = new TreeMap<>();
        parma.put("username", username);
        parma.put("password", mD5password);
        parma.put("rememberMe", "true");
        return mApiService.login(Constant.BASE_URL, ParmaUtil.getParma(parma));
    }
}
