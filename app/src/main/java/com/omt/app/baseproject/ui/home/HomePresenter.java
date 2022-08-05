package com.omt.app.baseproject.ui.home;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.omt.app.baseproject.R;
import com.omt.app.baseproject.base.mvp.BasePresenter;
import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;
import com.omt.app.baseproject.di.model.ListTask;
import com.omt.app.baseproject.di.model.Topic;
import com.omt.app.baseproject.ui.home.adapter.StatusTask;
import com.omt.app.baseproject.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

public class HomePresenter<V extends HomeView> extends BasePresenter<V> implements IHomePresenter<V> {

    private ListTask listTask;
    private List<Topic> topics;

    @Inject
    public HomePresenter(ApiHelper apiHelper, SharePreferencesHelper preferencesHelper) {
        super(apiHelper, preferencesHelper);
    }

    @Override
    public void getTasks(Context activity, Integer page) {
        if (page == null || page < 1) page = 1;
        String json = StringUtils.getStringFromRaw(activity, R.raw.list_task);
        listTask = new Gson().fromJson(json, ListTask.class);
        getView().bindTask(listTask);
    }


    private void getTopic(Context activity) {
        String json = StringUtils.getStringFromRaw(activity, R.raw.list_topic);
        topics = new Gson().fromJson(json, new TypeToken<List<Topic>>() {
        }.getType());
        getView().bindTopic(topics);
    }

    @Override
    public void searchTask(StatusTask statusTask, Topic topic, String toString) {

    }

    public List<Topic> getTopics() {
        return topics;
    }

    @Override
    public void getData(Context activity, int page) {
        getTasks(activity, page);
        getTopic(activity);
    }

    @Override
    public void backPageTask(Context homeActivity) {
        getTasks(homeActivity, listTask.getCurrentPage() - 1);
    }

    @Override
    public void nextPageTask(Context homeActivity) {
        getTasks(homeActivity, listTask.getCurrentPage() + 1);
    }

    @Override
    public void creterTask(HomeActivity homeActivity, String name, String describe, String dateTime, Topic topic) {
        getTasks(homeActivity, listTask.getCurrentPage());
    }

}
