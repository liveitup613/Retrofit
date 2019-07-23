package com.todo.vidyanandmishra.retofitexample.commonutils;

import android.content.Context;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public class AppPreferenceManager {

    private static final String SESSION_KEY = "SESSION_KEY";

    private AppPreferences appPreference;

    private static AppPreferenceManager appPreferenceManager;

    private AppPreferenceManager(Context context) {
        appPreference = AppPreferences.getAppPreferences(context);
    }

    public static AppPreferenceManager getInstance() {
        return appPreferenceManager;
    }

    public static void setAppPreferenceManager(Context context) {
        appPreferenceManager = new AppPreferenceManager(context);
    }

    public void clearAllPreferences() {
        appPreference.clearPreferences();
    }

    public boolean setSessionKey(String sessionKey) {
        return appPreference.putString(SESSION_KEY, sessionKey);
    }

    public boolean hasSession() {
        boolean hasSession = getSessionKey() != null ? true : false;
        return hasSession;
    }

    public String getSessionKey() {
        return appPreference.getString(SESSION_KEY, null);
    }

    public boolean removeSessionKey() {
       return appPreference.remove(SESSION_KEY);
    }
}
