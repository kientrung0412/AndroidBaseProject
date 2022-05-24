package com.omt.app.baseproject.di.module;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;
import com.omt.app.baseproject.ui.home.HomeActivity;
import com.omt.app.baseproject.ui.home.HomeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelModule {
    @Provides
    ViewModelProvider.Factory provideViewModelProvider(HomeViewModel viewModel) {
        return  ViewModelProvider.Factory
    }

}
