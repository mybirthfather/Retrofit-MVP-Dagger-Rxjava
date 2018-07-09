package com.lrl.app.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.lrl.app.App;

import java.util.List;

/**
 * Created by ZHP on 2017/6/24.
 */
public class UIUtil {

    public static Context getContext() {
        return App.getApplication();
    }

    public static Thread getMainThread() {
        return App.getMainThread();
    }

    private static long getMainThreadId() {
        return App.getMainThreadId();
    }

    /**
     * 把 xxxdip转成px
     * @param value
     * @return
     */
    public static int dip2px(String value) {
        if (value.contains("dip")) {
            String dip = value.replace("dip", "");
            float dp = Float.parseFloat(dip);
            return dip2px((int) dp);
        }
        return -1;
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    private static Handler getHandler() {
        return App.getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    private static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        if (App.mForegroundActivity == null) {
            return getContext().getResources().getString(resId);
        } else {
            return App.mForegroundActivity.getResources().getString(resId);
        }
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    // 判断当前的线程是不是在主线程
    private static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void startActivity(Intent intent) {

        Activity activity = App.getmForegroundActivity();
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
//    public static void showToastSafe(final int resId) {
//        showToastSafe(getString(resId));
//    }

//    /**
//     * 对toast的简易封装。线程安全，可以在非UI线程调用。
//     */
//    public static void showToastSafe(final String str) {
//        if (isRunInMainThread()) {
//            showToast(str);
//        } else {
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    showToast(str);
//                }
//            });
//        }
//    }

//    /**
//     * 对toast的简易封装。线程安全，可以在非UI线程调用。
//     */
//    public static void showLongToastSafe(final String str) {
//        if (isRunInMainThread()) {
//            showLongToast(str);
//        } else {
//            post(new Runnable() {
//                @Override
//                public void run() {
//                    showLongToast(str);
//                }
//            });
//        }
//    }


    /**
     * 获取控件宽度
     *
     * @param v
     * @return
     */
    public static int getWidth(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredWidth();
    }

    /**
     * 获取控件高度
     *
     * @param v
     * @return
     */
    public static int getHeight(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredHeight();
    }
    public static String getVersionName(){

        String versionName = null;
        try {
            PackageManager packageManager = getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            versionName = packageInfo.versionName;
        }catch (Exception e){

        }

        return versionName;
    }

    /**
     * 程序是否在后台运行
     * @param context
     * @return
     */
    public static boolean isAppIsInBackground(Context context) {
       boolean isInBackground = true;  
       ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
       if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
           List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
           for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
               //前台程序  
               if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                   for (String activeProcess : processInfo.pkgList) {
                       if (activeProcess.equals(context.getPackageName())) {  
                           isInBackground = false;  
                       }  
                   }  
               }  
           }  
       } else {  
           List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
           ComponentName componentInfo = taskInfo.get(0).topActivity;
           if (componentInfo.getPackageName().equals(context.getPackageName())) {  
               isInBackground = false;  
           }  
       }  
  
       return isInBackground;  
   }

}
