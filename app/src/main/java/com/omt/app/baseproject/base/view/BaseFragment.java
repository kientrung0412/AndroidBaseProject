package com.omt.app.baseproject.base.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

import android.util.AttributeSet;
import android.view.View;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<VB extends ViewBinding> extends DaggerFragment {

    protected VB binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        binding = getViewBinding();
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    protected abstract VB getViewBinding();

    protected abstract void bindViews();

}