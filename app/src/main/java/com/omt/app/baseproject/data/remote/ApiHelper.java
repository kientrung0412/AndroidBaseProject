package com.omt.app.baseproject.data.remote;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ApiHelper {

    @Inject
    SharePreferencesHelper preferencesHelper;

    @Inject
    public ApiHelper() {
    }

    public Observable<List<Map<String, Object>>> getData() {
        return AppApi.getInstance().getUsers();
    }
}
