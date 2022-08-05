package com.omt.app.baseproject.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omt.app.baseproject.R;
import com.omt.app.baseproject.base.mvp.BaseActivity;
import com.omt.app.baseproject.base.view.OnClickItemListener;
import com.omt.app.baseproject.databinding.ActivityHomeBinding;
import com.omt.app.baseproject.di.model.ListTask;
import com.omt.app.baseproject.di.model.Task;
import com.omt.app.baseproject.di.model.Topic;
import com.omt.app.baseproject.ui.home.adapter.StatusTask;
import com.omt.app.baseproject.ui.home.adapter.TaskAdapter;
import com.omt.app.baseproject.ui.home.dialog.CreateTaskDialog;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity<HomeView, HomePresenter<HomeView>, ActivityHomeBinding> implements HomeView, View.OnClickListener {

    private TaskAdapter taskAdapter;

    @Override
    protected ActivityHomeBinding setupViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void bindViews() {
        viewBinding.btnSearch.setOnClickListener(this);
        viewBinding.btnBack.setOnClickListener(this);
        viewBinding.btnNext.setOnClickListener(this);
        viewBinding.btnAddTask.setOnClickListener(this);
        getPresenter().getData(this, 0);

        ArrayAdapter<StatusTask> taskArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, StatusTask.values());
        viewBinding.spinnerStatus.setAdapter(taskArrayAdapter);
    }

    @Override
    protected HomeView getViewMvp() {
        return this;
    }

    @Override
    public void bindTask(ListTask listTask) {
        if (taskAdapter == null) {
            if (listTask.getCurrentPage() == 1)
                viewBinding.btnBack.setVisibility(View.GONE);
            else viewBinding.btnBack.setVisibility(View.VISIBLE);

            taskAdapter = new TaskAdapter(listTask.getTasks()).setOnClickListener(new OnClickItemListener<Task>() {
                @Override
                public void onClickItem(Task obj) {

                }
            });
            viewBinding.rcvTask.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            viewBinding.rcvTask.setAdapter(taskAdapter);
        } else
            taskAdapter.reBind(listTask.getTasks());
    }

    @Override
    public void bindTopic(List<Topic> topic) {
        ArrayAdapter<Topic> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, topic);
        viewBinding.spinnerTopic.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                getPresenter().searchTask(((StatusTask) viewBinding.spinnerStatus.getSelectedItem()), ((Topic) viewBinding.spinnerTopic.getSelectedItem()), viewBinding.edtKeywordSearch.getText().toString());
                break;
            case R.id.btn_add_task:
                CreateTaskDialog createTaskDialog = new CreateTaskDialog().setOnClickYesListener((name, describe, dateTime, topic) -> getPresenter().creterTask(this, name, describe, dateTime, topic));
                Bundle bundle = new Bundle();
                bundle.putBoolean(CreateTaskDialog.EXTRA_IS_CREATE, true);
                bundle.putParcelableArrayList(CreateTaskDialog.EXTRA_TOPICS, new ArrayList<>(getPresenter().getTopics()));
                createTaskDialog.setArguments(bundle);
                createTaskDialog.show(getSupportFragmentManager(), "CreateTaskDialog");
                break;
            case R.id.btn_back:
                getPresenter().backPageTask(this);
                break;
            case R.id.btn_next:
                getPresenter().nextPageTask(this);
                break;
        }
    }
}