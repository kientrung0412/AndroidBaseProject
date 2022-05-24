package com.omt.app.baseproject.data.pref;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.omt.app.baseproject.utils.Const;

import javax.inject.Inject;

public class SharePreferencesHelper {

    private final SharedPreferences pref;

    @Inject
    public SharePreferencesHelper(@NonNull Context context) {
        pref = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        pref.edit().putString(key, value).apply();
    }

    public void putInteger(String key, Integer value) {
        if (value == null) value = 0;
        pref.edit().putInt(key, value).apply();
    }

    public void putFloat(String key, Float value) {
        if (value == null) value = 0f;
        pref.edit().putFloat(key, value).apply();
    }

    public void putLong(String key, Long value) {
        if (value == null) value = 0L;
        pref.edit().putLong(key, value).apply();
    }

    public void putBoolean(String key, Boolean value) {
        if (value == null) value = false;
        pref.edit().putBoolean(key, value).apply();
    }

    public String getString(String key) {
        return pref.getString(key, null);
    }

    public Long getLong(String key) {
        return pref.getLong(key, 0L);
    }

    public Boolean getBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public Float getFloat(String key) {
        return pref.getFloat(key, 0f);
    }

    public Integer getInteger(String key) {
        return pref.getInt(key, 0);
    }


}
