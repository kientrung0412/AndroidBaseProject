package com.omt.app.baseproject.di.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListTask {

    @SerializedName("totalPage")
    private Integer totalPage;
    @SerializedName("currentPage")
    private Integer currentPage;
    @SerializedName("tasks")
    private List<Task> tasks;

    public Integer getTotalPage() {
        return totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
