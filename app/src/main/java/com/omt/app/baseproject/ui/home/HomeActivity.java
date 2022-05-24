package com.omt.app.baseproject.ui.home;


import androidx.lifecycle.ViewModelProvider;

import com.omt.app.baseproject.base.mvvm.BaseActivity;
import com.omt.app.baseproject.databinding.ActivityHomeBinding;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {

    @Inject
    ViewModelProvider.Factory factory;

    @Override
    protected ActivityHomeBinding getViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected HomeViewModel createViewModel() {
        return null;
    }

    @Override
    protected void bindViews() {
        viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
    }

}