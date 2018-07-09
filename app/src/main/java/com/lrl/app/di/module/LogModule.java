package com.lrl.app.di.module;


import com.lrl.app.http.ApiService;
import com.lrl.app.http.LogModel;
import com.lrl.app.presenter.contract.LogContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jiabaofeng on 2017/11/6.
 */
@Module
public class LogModule {

    private LogContract.LogView mView;

    public LogModule(LogContract.LogView mView) {
        this.mView = mView;
    }

    @Provides
    public LogContract.LogView provideView(){
        return mView;
    }
    @Provides
    public LogContract.ILogModel provideModel(ApiService apiService){

        return new LogModel(apiService);
    }
}
