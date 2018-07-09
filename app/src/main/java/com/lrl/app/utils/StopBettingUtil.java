package com.lrl.app.utils;

import android.text.TextUtils;

/**
 * 封盘显示
 * Created by jiabaofeng on 2017/9/26.
 */

public class StopBettingUtil {
    public static String getStopBettingStr(String input) {
        if(TextUtils.isEmpty(input)){
            return "";
        }
        if(!"封盘".equals(input)){
            return "¥ "+input;
        }
        return input;
    }
}
