package com.lrl.app.ui;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.lrl.app.App;
import com.lrl.app.R;
import com.lrl.app.di.module.component.AppComponent;
import com.lrl.app.presenter.contract.BasePresenter;
import com.lrl.app.utils.StatusBarUtil;
import com.lrl.app.widget.BaseView;
import com.lrl.app.widget.TouchInterceptLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by ZHP on 2017/6/24.
 */

public abstract class BaseActivity<T extends BasePresenter> extends BaseKeyBoardActivity implements BaseView {



    protected App mApplication;

    private FrameLayout mRootView;

    private FrameLayout mViewContent;

    private TouchInterceptLayout mProgress;
    private boolean showLoading = true;
    protected boolean onResume = true;
    @Inject
    protected T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(setLayoutView());

        this.mApplication = (App) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
//        getWindow().setBackgroundDrawable(null);
        //设置状态栏颜色
        setStatusBar();
        //设置状态栏文字颜色及图标为深色
//        StatusBarUtil.StatusBarLightMode(this);
        init();

    }

    /**
     * 子类可选择性设置状态栏颜色
     * 默认是透明，相当于没有状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.transparencyBar(this);
    }

    protected View setLayoutView() {

        mRootView = (FrameLayout) View.inflate(this, R.layout.base_progress,null);

        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);

        mProgress = (TouchInterceptLayout) mRootView.findViewById(R.id.progress);

        setRealContentView();
        return mRootView;
    }

    private void setRealContentView() {

        View realContentView=  LayoutInflater.from(this).inflate(setLayout(),mViewContent,true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        onResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        onResume = false;

    }

    /**
     * 申请SD卡写入权限
     * @param consumer 权限结果回调
     */
    public static void requestSdWritePermissionStatic(Context context, Consumer<Boolean> consumer){
        // 检查权限
        RxPermissions.getInstance(context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.CHANGE_NETWORK_STATE)
                .subscribe(consumer);

    }

    /**
     * 申请SD卡写入权限
     * @param consumer 权限结果回调
     */
    public void requestSdWritePermission(Consumer<Boolean> consumer){
        requestSdWritePermissionStatic(this,consumer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if(null != mPresenter){
            mPresenter.detachView();
        }
    }

    public abstract int setLayout();


    public abstract void init();

    @Override
    public void showLoading() {
        if(showLoading)
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String msg) {
        mProgress.setVisibility(View.GONE);

    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
    }

    @Override
    public void dismissLoading() {
        mProgress.setVisibility(View.GONE);
    }


    public abstract void setupActivityComponent(AppComponent appComponent);
    public boolean isOnResume() {
        return onResume;
    }

}
