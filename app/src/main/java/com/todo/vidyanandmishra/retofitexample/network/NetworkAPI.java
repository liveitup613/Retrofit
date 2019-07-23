package com.todo.vidyanandmishra.retofitexample.network;

import com.todo.vidyanandmishra.retofitexample.models.LoginRequestModel;
import com.todo.vidyanandmishra.retofitexample.models.SuccessResponseModel;
import com.todo.vidyanandmishra.retofitexample.models.UpdateUserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.todo.vidyanandmishra.retofitexample.network.ApiConstants.GET_CONFIG;
import static com.todo.vidyanandmishra.retofitexample.network.ApiConstants.LOGIN;
import static com.todo.vidyanandmishra.retofitexample.network.ApiConstants.LOGOUT;
import static com.todo.vidyanandmishra.retofitexample.network.ApiConstants.UPDATE_USER;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public interface NetworkAPI {

    @POST(LOGIN)
    Call<SuccessResponseModel> callLogin(@Body LoginRequestModel loginRequestModel);

    @GET(GET_CONFIG)
    Call<SuccessResponseModel> getConfig();

    @POST(LOGOUT)
    Call<SuccessResponseModel> logout();

    //PUT request [You can append any field with ULR like this]
    @PUT(UPDATE_USER+"{_id}/")
    Call<SuccessResponseModel> updateUser(@Path("_id") String userId, @Body UpdateUserModel updateTrackModel);
}
