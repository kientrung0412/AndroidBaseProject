package com.omt.app.baseproject.ui.home;

import android.content.Context;

import com.omt.app.baseproject.base.mvp.BaseIPresenter;
import com.omt.app.baseproject.di.model.Topic;
import com.omt.app.baseproject.ui.home.adapter.StatusTask;

public interface IHomePresenter<V extends HomeView> extends BaseIPresenter<V> {
    void getTasks(Context context, Integer page);

    void searchTask(StatusTask statusTask, Topic topic, String toString);

    void getData(Context context, int i);

    void backPageTask(Context homeActivity);

    void nextPageTask(Context homeActivity);

    void creterTask(HomeActivity homeActivity, String name, String describe, String dateTime, Topic topic);
}


