package com.omt.app.baseproject.ui.home.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.blankj.utilcode.util.StringUtils;
import com.omt.app.baseproject.R;
import com.omt.app.baseproject.databinding.DialogCreaterTaskBinding;
import com.omt.app.baseproject.di.model.Task;
import com.omt.app.baseproject.di.model.Topic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateTaskDialog extends DialogFragment {
    public static final String EXTRA_IS_CREATE = "extra.IS_CREATE";
    public static final String EXTRA_TOPICS = "extra.TOPICS";
    public static final String EXTRA_TASK = "extra.TASK";

    private boolean isCreate;
    private ArrayList<Topic> topics;
    private OnClickYesListener onClickYesListener;

    private ImageView ivClose;
    private AppCompatEditText editNameTask;
    private AppCompatEditText editDescribeTask;
    private AppCompatTextView tvDateTaskSelect;
    private AppCompatSpinner spinnerTopicTask;
    private Button btnYes;
    private Button btnClose;
    private Task task;

    public CreateTaskDialog setOnClickYesListener(OnClickYesListener onClickYesListener) {
        this.onClickYesListener = onClickYesListener;
        return this;
    }

    public CreateTaskDialog() {
        super(R.layout.dialog_creater_task);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() == null) return;
        isCreate = getArguments().getBoolean(EXTRA_IS_CREATE);
        topics = getArguments().getParcelableArrayList(EXTRA_TOPICS);
        task = getArguments().getParcelable(EXTRA_TASK);

        initView(view);
    }


    private void initView(View view) {
        ((TextView) view.findViewById(R.id.tv_title_dialog)).setText(isCreate ? "Tạo task" : "Sửa task");
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        editNameTask = (AppCompatEditText) view.findViewById(R.id.edit_name_task);
        editDescribeTask = (AppCompatEditText) view.findViewById(R.id.edit_describe_task);
        tvDateTaskSelect = (AppCompatTextView) view.findViewById(R.id.tv_date_task_select);
        spinnerTopicTask = (AppCompatSpinner) view.findViewById(R.id.spinner_topic_task);
        btnYes = (Button) view.findViewById(R.id.btn_yes);
        btnClose = (Button) view.findViewById(R.id.btn_close);

        btnClose.setOnClickListener(v -> dismiss());
        ivClose.setOnClickListener(v -> dismiss());
        btnYes.setOnClickListener(v -> {
            if (editNameTask.getText() == null || StringUtils.isTrimEmpty(editNameTask.getText().toString())) {
                Toast.makeText(getContext(), "Thiếu tên", Toast.LENGTH_SHORT).show();
                return;
            }
            if (editDescribeTask.getText() == null || StringUtils.isTrimEmpty(editDescribeTask.getText().toString())) {
                Toast.makeText(getContext(), "Thiếu mô tả", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tvDateTaskSelect.getText() == null || StringUtils.isTrimEmpty(tvDateTaskSelect.getText().toString())) {
                Toast.makeText(getContext(), "Thiếu ngày", Toast.LENGTH_SHORT).show();
                return;
            }
            onClickYesListener.onClickYes(editNameTask.getText().toString(), editDescribeTask.getText().toString(), tvDateTaskSelect.getText().toString(), ((Topic) spinnerTopicTask.getSelectedItem()));
            dismiss();
        });
        tvDateTaskSelect.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year, month, dayOfMonth) -> {

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view2, hourOfDay, minute) -> {

                    tvDateTaskSelect.setText(String.format("%s:%s %s/%s/%s", hourOfDay, minute, dayOfMonth, month, year));

                }, Calendar.getInstance().get(Calendar.HOUR),
                        Calendar.getInstance().get(Calendar.MINUTE),
                        true);

                timePickerDialog.show();
            }, Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });
        ArrayAdapter<Topic> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, topics);
        spinnerTopicTask.setAdapter(adapter);

        if (task != null) {
            editDescribeTask.setText(task.getContent());
            editNameTask.setText(task.getTitle());
            tvDateTaskSelect.setText(task.getDuedate());
            spinnerTopicTask.setSelection(topics.indexOf(task.getTag()));
        }
    }

    public interface OnClickYesListener {
        void onClickYes(String name, String describe, String dateTime, Topic topic);
    }
}
