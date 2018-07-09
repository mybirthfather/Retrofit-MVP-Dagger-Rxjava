package com.lrl.app.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2017/6/24.
 */

@Module
public class AppModule {


    private Application mApplication;


    public AppModule(Application application){

        this.mApplication = application;

    }

    @Provides
    @Singleton
    public Application provideApplication(){

        return  mApplication;
    }



    @Provides
    @Singleton
    public Gson provideGson(){

        Gson gson = new GsonBuilder()

                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                    @Override
                    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                        if (src == src.longValue())
                            return new JsonPrimitive(src.longValue());

                        return new JsonPrimitive(src);
                    }
                })
                .create();

        return gson;

    }
}
