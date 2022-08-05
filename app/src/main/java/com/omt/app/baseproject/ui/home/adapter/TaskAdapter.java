package com.omt.app.baseproject.ui.home.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.StringUtils;
import com.omt.app.baseproject.R;
import com.omt.app.baseproject.base.view.OnClickItemListener;
import com.omt.app.baseproject.databinding.ItemTaskBinding;
import com.omt.app.baseproject.di.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private OnClickItemListener<Task> onClickListener;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskAdapter setOnClickListener(OnClickItemListener<Task> onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding itemBinding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindViews(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.size(tasks);
    }

    public void reBind(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameTask;
        TextView tvDateTask;
        TextView tvStatusTask;

        private Task task;

        public TaskViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            tvNameTask = binding.tvNameTask;
            tvDateTask = binding.tvDateDoneTask;
            tvStatusTask = binding.tvStatusTask;

            binding.ivMoreAction.setOnClickListener(v -> {
                if (task == null) return;
                PopupMenu popupMenu = new PopupMenu(tvDateTask.getContext(), binding.ivMoreAction);
                if (StatusTask.CLOSE == StatusTask.valueOf(task.getStatus()))
                    popupMenu.inflate(R.menu.menu_action_close);
                else
                    popupMenu.inflate(R.menu.menu_action_open);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.it_update:

                                break;
                        }


                        return true;
                    }
                });
                onClickListener.onClickItem(task);
                popupMenu.show();
            });
        }

        public void bindViews(Task task) {
            this.task = task;
            if (task == null) return;
            tvNameTask.setText(task.getTitle());
            if (StringUtils.isTrimEmpty(task.getDuedate())) {
                tvDateTask.setVisibility(View.GONE);
            } else {
                tvDateTask.setText(String.format("Ngày hoàn thành: %s", task.getTitle()));
                tvDateTask.setVisibility(View.VISIBLE);
            }

            tvStatusTask.setText(String.format("Trạng thái: %s", StatusTask.valueOf(task.getStatus()).getNameStatus()));

        }
    }

    public interface OnClickListener {
        void onCLickEdit(Task task);
    }
}
