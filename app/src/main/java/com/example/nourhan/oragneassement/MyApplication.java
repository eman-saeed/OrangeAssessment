package com.example.nourhan.oragneassement;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Eman on 8/16/2017.
 */

public class MyApplication extends Application {

    private static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RealmConfiguration getRealmContext(){
        return realmConfiguration;
    }
}
