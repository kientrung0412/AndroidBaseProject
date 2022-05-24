package com.omt.app.baseproject.base.ui;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import dagger.android.AndroidInjection;
import dagger.android.DaggerActivity;

public abstract class BaseActivity<B extends ViewBinding> extends AppCompatActivity {

    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AndroidInjection.inject(this);

        setupViews();
        bindViews();
    }

    private void setupViews() {
        binding = getViewBinding();
        View view = binding.getRoot();
        setContentView(view);
    }

    protected void requestStoragePermissions(MultiplePermissionsListener listener) {
        requestPermissions(listener, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    protected void requestPermissions(MultiplePermissionsListener listener, String... permissions) {
        Dexter.withContext(this)
                .withPermissions(permissions)
                .withListener(listener)
                .check();
    }

    protected abstract B getViewBinding();

    protected abstract void bindViews();

}
