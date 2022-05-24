package com.omt.app.baseproject.home;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.omt.app.baseproject.base.ui.BaseActivity;
import com.omt.app.baseproject.databinding.ActivityHomeBinding;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    @Override
    protected ActivityHomeBinding getViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void bindViews() {
        zipImage();
        requestStoragePermissions(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Matisse.from(HomeActivity.this)
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(50)
//                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.8f)
                        .imageEngine(new GlideEngine())
                        .forResult(112);
            }
          @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 112 && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
        }
    }

    private void zipImage() {
        try {
            List<String> strings = new ArrayList<>();
            strings.add("/sdcard/Download/ 1demo.heic");
            strings.add("/sdcard/Download/ 2demo.heic");
            strings.add("/sdcard/Download/ 3demo.heic");
            strings.add("/sdcard/Download/demo.heic");
            downQualityObservable(strings)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Object o) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private String downQualityImage(String path) throws IOException {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        OutputStream os = new BufferedOutputStream(new FileOutputStream("/sdcard/Download/" + System.currentTimeMillis() + ".jpg"));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os);
        os.close();

        return "output";
    }

    private @NonNull Observable<String> toObservable(String path) {
        return Observable.just(path).map(this::downQualityImage).subscribeOn(Schedulers.newThread());
    }

    private Observable<Object> downQualityObservable(List<String> paths) {
        return Observable.fromArray(paths.toArray(new String[0])).flatMap(this::toObservable, 4);
    }


}