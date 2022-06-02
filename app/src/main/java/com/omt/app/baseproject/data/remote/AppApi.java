package com.omt.app.baseproject.data.remote;

import androidx.annotation.NonNull;

import com.omt.app.baseproject.BuildConfig;
import com.omt.app.baseproject.utils.Const;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class AppApi {

    private static ApiService apiService;

    protected static ApiService getInstance() {
        if (apiService == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder()
                    .baseUrl(Const.DOMAIN)
                    .client(buildHeader())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
            if (BuildConfig.DEBUG) retrofit.client(buildLog());
            apiService = retrofit.build().create(ApiService.class);
        }
        return apiService;
    }

    @NonNull
    protected static OkHttpClient buildHeader() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            return chain.proceed(requestBuilder.build());
        });
        return httpClient;
    }

    @NonNull
    protected static OkHttpClient buildLog() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    protected void clearService() {
        apiService = null;
    }
}
