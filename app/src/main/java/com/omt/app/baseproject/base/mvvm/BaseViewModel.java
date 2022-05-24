package com.omt.app.baseproject.base.mvvm;

import androidx.lifecycle.ViewModel;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;


public class BaseViewModel extends ViewModel {

    ApiHelper apiHelper;
    SharePreferencesHelper preferencesHelper;

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
