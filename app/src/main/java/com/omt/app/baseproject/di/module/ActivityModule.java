package com.omt.app.baseproject.di.module;

import com.omt.app.baseproject.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract HomeActivity injectHomeActivity();

}
