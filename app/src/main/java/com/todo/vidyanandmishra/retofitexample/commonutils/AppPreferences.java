package com.todo.vidyanandmishra.retofitexample.commonutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.todo.vidyanandmishra.retofitexample.R;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public class AppPreferences {

    private static AppPreferences appPreferences;

    private Context applicationContext;

    private SharedPreferences sharedPreferences;

    private AppPreferences(Context applicationContext) {
        this.applicationContext = applicationContext;

        Resources res = this.applicationContext.getResources();
        String preferencesName = res.getString(R.string.app_name);
        sharedPreferences = this.applicationContext.getSharedPreferences(
                preferencesName, Context.MODE_PRIVATE);
    }

    public static AppPreferences getAppPreferences(Context applicationContext) {

        if (appPreferences != null) {
            return appPreferences;
        }

        appPreferences = new AppPreferences(applicationContext);
        return appPreferences;
    }

    public boolean putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }


    public boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public float getBoolean(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public boolean remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public boolean clearPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        boolean isSuccess = editor.commit();
        return isSuccess;
    }
}
