package com.lrl.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.liulishuo.filedownloader.FileDownloader;
import com.lrl.app.di.module.AppModule;
import com.lrl.app.di.module.HttpModule;
import com.lrl.app.di.module.component.AppComponent;
import com.lrl.app.di.module.component.DaggerAppComponent;
import com.lrl.app.utils.AppCrashHandlerUtil;

import java.util.List;

/**
 * Created by ZHP on 2017/6/24.
 */

public class App extends MultiDexApplication {
    private static App mContext;
    public  static Activity mForegroundActivity;
    private static Handler mMainHandler;
    private static Thread mMainThread;
    private static int mMainThreadId;
    private static Looper mMainThreadLooper;
    private AppComponent mAppComponent;
    private int mFinalCount;

    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.init(this);
        HttpModule httpModule = new HttpModule();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(httpModule).build();
        mContext = (App) getApplicationContext();
        mMainHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mMainThreadLooper = getMainLooper();
        //7.0系统通过uri访问时设置，防止文件不能访问
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        //crash
        AppCrashHandlerUtil.getInstance().init(this);
        //客服init
//        DemoHelper.getInstance().init(UIUtil.getContext());
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                mFinalCount++;
                //如果mFinalCount ==1，说明是从后台到前台
                if (mFinalCount == 1){
                    //说明从后台回到了前台\ 保存进入时间，用于统计
//                    ACache.get(getApplication()).put(Constant.APP_START_TIME,(System.currentTimeMillis()/1000)+"");
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mFinalCount--;
                //如果mFinalCount ==0，说明是前台到后台
                if (mFinalCount == 0){
                    //切换到后台，提交用户浏览时间，发送消息到MainActivity,进行服务器提交
//                    RxBusEvent.getDefault().post("appListener");
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }



    /**
     * 获取全局上下文
     *
     * @return
     */
    public static App getApplication() {
        return mContext;
    }

    /**
     * 获取主线程handler
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainHandler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程id
     *
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程轮询器
     *
     * @return
     */
    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }


    public static Activity getmForegroundActivity() {
        return mForegroundActivity;
    }

    public static void setmForegroundActivity(Activity mForegroundActivity) {
        App.mForegroundActivity = mForegroundActivity;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
