package com.softdesign.devintensive.data.managers;

import android.content.SharedPreferences;

import com.softdesign.devintensive.utils.ConstantManager;
import com.softdesign.devintensive.utils.DevIntensiveApplication;

import java.util.ArrayList;
import java.util.List;

public class PreferencesManager {

    private static final String[] USER_FIELDS = {
            ConstantManager.USER_PHONE_KEY,
            ConstantManager.USER_EMAIL_KEY,
            ConstantManager.USER_VK_PROFILE_KEY,
            ConstantManager.USER_GIT_KEY,
            ConstantManager.USER_ABOUT_KEY
    };
    private SharedPreferences mSharedPreferences;

    public PreferencesManager() {
        this.mSharedPreferences = DevIntensiveApplication.getSharedPreferences();
    }

    public void saveUserProfileData(List<String> userFields) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        for (int i = 0; i < USER_FIELDS.length; i++) {
            editor.putString(USER_FIELDS[i], userFields.get(i));
        }
        editor.apply();
    }

    public List<String> loadUserProfileData() {
        List<String> userFields = new ArrayList<>();
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_PHONE_KEY, ""));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_EMAIL_KEY, ""));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_VK_PROFILE_KEY, ""));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_GIT_KEY, ""));
        userFields.add(mSharedPreferences.getString(ConstantManager.USER_ABOUT_KEY, ""));
        return userFields;
    }


}
