package com.todo.vidyanandmishra.retofitexample;

import android.app.Application;

import com.todo.vidyanandmishra.retofitexample.commonutils.AppPreferenceManager;
import com.todo.vidyanandmishra.retofitexample.network.NetworkService;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public class ApplicationControl extends Application {

    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();
        AppPreferenceManager.setAppPreferenceManager(this);
        networkService = new NetworkService(getApplicationContext());
    }

    public NetworkService getNetworkService() {

        if(networkService == null) {
            networkService = new NetworkService(getApplicationContext());
        }

        return networkService;
    }
}
