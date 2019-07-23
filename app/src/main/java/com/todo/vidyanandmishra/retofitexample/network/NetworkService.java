package com.todo.vidyanandmishra.retofitexample.network;


import android.content.Context;
import android.util.Log;

import com.todo.vidyanandmishra.retofitexample.commonutils.AppPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public class NetworkService implements ApiConstants {

    private static final String TAG = "NetworkService";

    private NetworkAPI networkAPI;
    private OkHttpClient okHttpClient;
    private Context mContext;

    public NetworkService(Context context) {
        this(BASE_URL);
        this.mContext = context;
    }

    public NetworkService(String baseUrl) {

        okHttpClient = buildClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }

    /**
     * Getting API Interface
     * @return api interface
     */
    public NetworkAPI getAPI() {
        return networkAPI;
    }

    /**
     * Building and returning an OkHttpClient and
     * adding custom headers in efficient manner
     * @return okhttpclient
     */
    public OkHttpClient buildClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Bellow five lines is only for debugging purpose
        // If you want to print url, header etc on console
        // If required you can keep of remove
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        builder.addInterceptor(logging);

        builder.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request;
                AppPreferenceManager appPref = AppPreferenceManager.getInstance();

                // Here, can add multiple headers as per your need.
                // If user session is valid it will execute if{} otherwise else{}
                if (appPref.hasSession()) {
                    request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader(X_API_KEY, X_API_VALUE)
                            .addHeader(X_SESSION_KEY, appPref.getSessionKey()).build();
                } else {
                    request = chain.request().newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader(X_API_KEY, X_API_VALUE).build();
                }

                // This if{} is only to print the request body if you want to.
                if (request.body() != null) {
                    Buffer buffer = new Buffer();
                    request.body().writeTo(buffer);
                    String body = buffer.readUtf8();
                    Log.i(TAG, "intercept: Request Body: " + body);
                }

                return chain.proceed(request);
            }
        });

        return builder.build();
    }
}
