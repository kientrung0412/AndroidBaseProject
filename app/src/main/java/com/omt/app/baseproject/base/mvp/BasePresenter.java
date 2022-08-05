package com.omt.app.baseproject.base.mvp;

import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;

import javax.inject.Inject;

public class BasePresenter<V extends BaseView> implements BaseIPresenter<V> {

    @Inject
    ApiHelper apiHelper;
    @Inject
    SharePreferencesHelper preferencesHelper;

    private V view;

    @Inject
    public BasePresenter(ApiHelper apiHelper, SharePreferencesHelper preferencesHelper) {
        this.apiHelper = apiHelper;
        this.preferencesHelper = preferencesHelper;
    }


    public void attachView(V mView) {
        this.view = mView;
    }

    public V getView() {
        return view;
    }
}
