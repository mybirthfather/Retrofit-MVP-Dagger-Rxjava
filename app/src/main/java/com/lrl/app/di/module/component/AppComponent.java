package com.lrl.app.di.module.component;

import android.app.Application;

import com.lrl.app.di.module.AppModule;
import com.lrl.app.di.module.HttpModule;
import com.lrl.app.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ZHP on 2017/6/24.
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();


    public Application getApplication();
}
