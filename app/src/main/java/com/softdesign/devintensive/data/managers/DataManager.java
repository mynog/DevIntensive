package com.softdesign.devintensive.data.managers;

public class DataManager {
    private static DataManager INSTANCE = null;

    private PreferencesManager mPreferencesManager = new PreferencesManager();

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

}
