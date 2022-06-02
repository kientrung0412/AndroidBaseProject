package com.omt.app.baseproject.data.remote;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface ApiService {

    @GET("users")
    Observable<List<Map<String, Object>>> getUsers();

}
