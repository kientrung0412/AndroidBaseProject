package com.omt.app.baseproject.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omt.app.baseproject.base.mvvm.BaseViewModel;
import com.omt.app.baseproject.base.mvvm.BaseViewModelFactory;
import com.omt.app.baseproject.data.pref.SharePreferencesHelper;
import com.omt.app.baseproject.data.remote.ApiHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<String> liveDataName;

    public HomeViewModel(ApiHelper apiHelper, SharePreferencesHelper preferencesHelper) {
        super(apiHelper, preferencesHelper);
    }

    public MutableLiveData<String> getName() {
        if (liveDataName == null) liveDataName = new MutableLiveData<>();
        getApiHelper().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Map<String, Object>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Map<String, Object>> maps) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return liveDataName;
    }

    public static class Factory extends BaseViewModelFactory {

        @Inject
        public Factory() {
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
            return (T) new HomeViewModel(apiHelper, preferencesHelper);
        }
    }
}
