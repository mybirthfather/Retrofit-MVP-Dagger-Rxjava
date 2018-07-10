package com.lrl.app.http;



import com.lrl.app.baen.BaseBean;
import com.lrl.app.baen.EmptyBean;
import com.lrl.app.baen.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by ZHP on 2017/6/25.
 */

public interface ApiService {

    //发送验证码
    @FormUrlEncoded
    @POST
    Observable<BaseBean<EmptyBean>> sendCode(@Url String path, @FieldMap Map<String, String> map);
    //给服务器传崩溃日志
    @FormUrlEncoded
    @POST
    Observable<BaseBean<EmptyBean>> addLog(@Url String path, @FieldMap Map<String, String> map);
    /**
     * 模拟的登陆 不用关心细节
     */
    @FormUrlEncoded
    @POST
    Observable<BaseBean<LoginBean>> login (@Url String path, @FieldMap Map<String, String> map);

}
