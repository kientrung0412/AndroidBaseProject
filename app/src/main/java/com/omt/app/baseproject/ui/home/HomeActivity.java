package com.omt.app.baseproject.ui.home;


import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.omt.app.baseproject.base.mvvm.BaseActivity;
import com.omt.app.baseproject.databinding.ActivityHomeBinding;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {

    @Inject
    HomeViewModel.Factory factory;

    @Override
    protected ActivityHomeBinding getViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomeViewModel createViewModel() {
        return new ViewModelProvider(this, factory).get(HomeViewModel.class);
    }

    @Override
    protected void bindViews() {
        viewModel.getName().observe(this, s -> binding.tvTitle.setText(s));
        binding.tvTitle.setOnClickListener(v -> viewModel.getName().setValue("Trung"));
    }
}