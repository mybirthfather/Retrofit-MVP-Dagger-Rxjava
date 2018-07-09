package com.lrl.app.utils;

import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;


/**
 * Created by ZHP on 2017/6/24.
 */

public class PermissionUtil {


    public static Observable<Boolean> requestPermisson(Context activity, String permission){


        RxPermissions rxPermissions =  RxPermissions.getInstance(activity);


        return rxPermissions.request(permission);
    }





}
