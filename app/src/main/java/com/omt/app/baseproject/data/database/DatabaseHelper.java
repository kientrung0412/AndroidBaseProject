package com.omt.app.baseproject.data.database;

import io.realm.RealmConfiguration;

public class DatabaseHelper {

    private RealmConfiguration config;

    public DatabaseHelper() {
        config = new RealmConfiguration.Builder().build();
    }

    public void putData() {
//        Realm.getInstanceAsync(config, new Realm.Callback() {
//            @Override
//            public void onSuccess(@NonNull Realm realm) {
//                realm.beginTransaction();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(@NonNull Realm realm) {
//realm.createObject()
//                    }
//                });
//            }
//        });
    }
}
