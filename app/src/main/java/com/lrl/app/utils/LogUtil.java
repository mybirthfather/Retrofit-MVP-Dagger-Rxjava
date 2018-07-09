package com.lrl.app.utils;


/**
 * Created by ZHP on 2017/6/24.
 */
public class LogUtil {

    public static void v(String tag, String msg) {
        if (Constant.SHOW_LOG)
            android.util.Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (Constant.SHOW_LOG)
            android.util.Log.v(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        if (Constant.SHOW_LOG)
            android.util.Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (Constant.SHOW_LOG)
            android.util.Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        if (Constant.SHOW_LOG)
            android.util.Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (Constant.SHOW_LOG)
            android.util.Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (Constant.SHOW_LOG)
            android.util.Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (Constant.SHOW_LOG)
            android.util.Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (Constant.SHOW_LOG)
            android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (Constant.SHOW_LOG)
            android.util.Log.e(tag, msg, t);
    }
}
