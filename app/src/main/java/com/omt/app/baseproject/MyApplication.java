package com.omt.app.baseproject;

import android.content.Context;


import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.omt.app.baseproject.di.component.DaggerAppComponent;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


@Singleton
public class MyApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().addContext(getApplicationContext()).build();
    }
}