package com.omt.app.baseproject.ui.home;

import com.omt.app.baseproject.base.mvvm.BaseViewModel;
import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    @Inject
    public HomeViewModel(ApiHelper apiHelper, SharePreferencesHelper preferencesHelper) {
        super(apiHelper, preferencesHelper);
    }
}
