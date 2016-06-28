package com.softdesign.devintensive.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class DevIntensiveApplication extends Application {
    public static SharedPreferences sSharedPreferences;

    public static SharedPreferences getSharedPreferences() {
        Log.d("Dev ---- ", "getSharedPreferences");
        return sSharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Dev ---- ", "onCreate");
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

}
