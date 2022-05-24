package com.omt.app.baseproject.data.remote;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;

import javax.inject.Inject;

public class ApiHelper {

    @Inject
    SharePreferencesHelper preferencesHelper;

    @Inject
    public ApiHelper() {
    }

    public Boolean getData() {
        return preferencesHelper.getBoolean("demo");
    }
}
