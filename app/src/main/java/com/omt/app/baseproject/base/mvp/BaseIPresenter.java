package com.omt.app.baseproject.base.mvp;

public interface BaseIPresenter<V extends BaseView> {

    void attachView(V view);

}
