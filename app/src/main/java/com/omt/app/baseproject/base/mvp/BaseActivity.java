package com.omt.app.baseproject.base.mvp;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.omt.app.baseproject.base.view.LoadingDialog;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<V extends BaseView, P extends BaseIPresenter<V>, VB extends ViewBinding> extends DaggerAppCompatActivity {

    @Inject
    P presenter;

    protected LoadingDialog loadingDialog;
    protected VB viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = setupViewBinding();
        setContentView(viewBinding.getRoot());
        presenter.attachView(getViewMvp());
        bindViews();
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

    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        if (!loadingDialog.isShowing()) loadingDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewBinding = null;
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract void bindViews();

    protected abstract V getViewMvp();

    protected abstract VB setupViewBinding();
}
