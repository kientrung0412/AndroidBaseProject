package com.omt.app.baseproject.ui.home;

import com.omt.app.baseproject.base.mvp.BaseView;
import com.omt.app.baseproject.di.model.ListTask;
import com.omt.app.baseproject.di.model.Topic;

import java.util.List;

public interface HomeView extends BaseView {
    void bindTask(ListTask listTask);

    void bindTopic(List<Topic> topic);
}
