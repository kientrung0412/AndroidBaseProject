package com.omt.app.baseproject.di.component;

import android.content.Context;

import com.omt.app.baseproject.MyApplication;
import com.omt.app.baseproject.di.module.ActivityModule;
import com.omt.app.baseproject.di.module.RemoteModule;
import com.omt.app.baseproject.di.module.StoreModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        RemoteModule.class,
        StoreModule.class,
        ActivityModule.class,
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder addContext(Context context);

        AppComponent build();
    }

}
