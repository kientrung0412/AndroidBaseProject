package com.omt.app.baseproject.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

import com.omt.app.baseproject.R;

public abstract class BaseDialog<VB extends ViewBinding> extends DialogFragment {

    protected VB binding;

    public BaseDialog() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getVIewBinding();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void show(FragmentActivity fragmentActivity) {
        super.show(fragmentActivity.getSupportFragmentManager(), fragmentActivity.getClass().getSimpleName());
    }

    protected abstract VB getVIewBinding();

    protected abstract void bindViews();

}
