package com.lrl.app.http;


import com.lrl.app.baen.BaseBean;
import com.lrl.app.baen.EmptyBean;
import com.lrl.app.presenter.contract.LogContract;
import com.lrl.app.utils.Constant;
import com.lrl.app.utils.ParmaUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by jiabaofeng on 2017/11/6.
 */

public class LogModel implements LogContract.ILogModel {

    private ApiService mApiService;

    public LogModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> addLog(String msg) {

        TreeMap<String,String> map = new TreeMap<>();
        String encode = null;
        try {
            encode = URLEncoder.encode(msg.trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("msg",encode);
        Map<String, String> parma = ParmaUtil.getParma(map);
        parma.put("msg",msg);

        return mApiService.addLog(Constant.BASE_URL, parma);//样例接口
    }
}
