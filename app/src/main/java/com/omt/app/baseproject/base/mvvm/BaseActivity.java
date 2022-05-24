package com.omt.app.baseproject.base.mvvm;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends DaggerAppCompatActivity {

    protected VB binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
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

    protected abstract VB getViewBinding();

    protected abstract VM createViewModel();

    protected abstract void bindViews();

}
