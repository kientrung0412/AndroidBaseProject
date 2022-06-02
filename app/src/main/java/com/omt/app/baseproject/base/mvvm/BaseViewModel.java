package com.omt.app.baseproject.base.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;
import com.omt.app.baseproject.ui.home.HomeViewModel;

import javax.inject.Inject;


public class BaseViewModel extends ViewModel {

    private final ApiHelper apiHelper;
    private final SharePreferencesHelper preferencesHelper;

    public BaseViewModel(ApiHelper apiHelper, SharePreferencesHelper preferencesHelper) {
        this.apiHelper = apiHelper;
        this.preferencesHelper = preferencesHelper;
    }

    protected ApiHelper getApiHelper() {
        return apiHelper;
    }

    protected SharePreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}
