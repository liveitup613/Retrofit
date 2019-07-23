package com.todo.vidyanandmishra.retofitexample.network;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.todo.vidyanandmishra.retofitexample.commonutils.AppPreferenceManager;
import com.todo.vidyanandmishra.retofitexample.commonutils.ErrorUtils;
import com.todo.vidyanandmishra.retofitexample.models.ErrorResponseModel;
import com.todo.vidyanandmishra.retofitexample.models.LoginRequestModel;
import com.todo.vidyanandmishra.retofitexample.models.SuccessResponseModel;
import com.todo.vidyanandmishra.retofitexample.models.UpdateUserModel;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vidyanandmishra on 13/12/16.
 *
 *  This is a Presenter call which will interact with
 *  both Network as well as the View
 *
 *  NOTE:
 *  In this class I have used some temp methods
 *  you can change as per your need
 */

public class Presenter {

    private static final String TAG = "Presenter";
    private Context mContext;
    private NetworkService service;

    public Presenter(Context context, NetworkService service) {
        this.mContext = context;
        this.service = service;
    }

    public interface OnResponseListener {
        void onSuccessResponse(Object response);
        void onFailureResponse(Object error);
    }

    /**
     * Method to call Login API
     * @param loginRequestModel
     * @param listener
     */
    public void login(LoginRequestModel loginRequestModel, final OnResponseListener listener) {

        Call<SuccessResponseModel> call = service.getAPI().callLogin(loginRequestModel);

        call.enqueue(new Callback<SuccessResponseModel>() {

            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {

                if (response.isSuccessful()) {
                    listener.onSuccessResponse(response.body());
                }else {
                    ErrorResponseModel errorModel = null;

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorModel = new Gson().fromJson(jObjError.toString(), ErrorResponseModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    listener.onFailureResponse(errorModel);
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                t.printStackTrace();
                listener.onFailureResponse(t);
            }
        });
    }

    /**
     * Method to call config API
     * @param listener
     */
    public void getConfig(final OnResponseListener listener) {

        Call<SuccessResponseModel> call = service.getAPI().getConfig();

        call.enqueue(new Callback<SuccessResponseModel>() {

            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {
                if (response.isSuccessful()) {
                    listener.onSuccessResponse(response.body());
                } else {
                    ErrorResponseModel errorModel = null;

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorModel = new Gson().fromJson(jObjError.toString(), ErrorResponseModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    listener.onFailureResponse(errorModel);
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                t.printStackTrace();
                listener.onFailureResponse(t);
            }
        });
    }

    /**
     * Calling Logout API
     */
    public void logout() {

        Call<SuccessResponseModel> call = service.getAPI().logout();

        call.enqueue(new Callback<SuccessResponseModel>() {

            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {

                if (response.isSuccessful()) {

                    //Removing current user session from pref
                    AppPreferenceManager.getInstance().removeSessionKey();

                    //If you want to clear all the preferences <As per your need>
                    AppPreferenceManager.getInstance().clearAllPreferences();

                    //TODO: DO YOUR THINGS
                } else {

                    try {

                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        ErrorResponseModel errModel = new Gson().fromJson(jObjError.toString(), ErrorResponseModel.class);

                        if (errModel != null) {
                            ErrorUtils.handleError(mContext, errModel, null);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {

                if (t instanceof IOException) {
                    Toast.makeText(mContext, "No internet connection available!", Toast.LENGTH_SHORT).show();
                }

                t.printStackTrace();
            }
        });
    }

    public void updateUser(String userId, UpdateUserModel updateUserModel, final OnResponseListener listener) {

        Call<SuccessResponseModel> call = service.getAPI().updateUser(userId, updateUserModel);

        call.enqueue(new Callback<SuccessResponseModel>() {

            @Override
            public void onResponse(Call<SuccessResponseModel> call, Response<SuccessResponseModel> response) {

                if (response.isSuccessful()) {
                    listener.onSuccessResponse(response.body());
                } else {
                    ErrorResponseModel errorModel = null;
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        errorModel = new Gson().fromJson(jObjError.toString(), ErrorResponseModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    listener.onFailureResponse(errorModel);
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseModel> call, Throwable t) {
                t.printStackTrace();
                listener.onFailureResponse(t);
            }
        });
    }
}
