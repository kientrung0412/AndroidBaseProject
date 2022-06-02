package com.omt.app.baseproject.base.mvvm;

import androidx.lifecycle.ViewModelProvider;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;

import javax.inject.Inject;

public abstract class BaseViewModelFactory implements ViewModelProvider.Factory {
    @Inject
    protected ApiHelper apiHelper;
    @Inject
    protected SharePreferencesHelper preferencesHelper;
}
