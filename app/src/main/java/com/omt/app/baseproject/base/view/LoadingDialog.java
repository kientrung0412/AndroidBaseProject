package com.omt.app.baseproject.base.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.omt.app.baseproject.R;

public class LoadingDialog extends AlertDialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.Loading_Dialog);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }
}
