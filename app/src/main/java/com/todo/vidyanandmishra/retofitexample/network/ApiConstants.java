package com.todo.vidyanandmishra.retofitexample.network;

/**
 * Created by vidyanandmishra on 13/12/16.
 *
 * NOTE:
 * I have taken some dummy end points just to show the type of request.
 * Please use your own & valid ULRs and Endpoints.
 */

public interface ApiConstants {

    String BASE_URL = "https://<Your base URL>";

    String X_API_KEY = "<YOUR X API KEY>";
    String X_API_VALUE = "<YOUR X API VALUE>";
    String X_SESSION_KEY = "<YOUR SESSION KEY>";

    // End Points
    String LOGIN = "/user/login";

    String GET_CONFIG = "/user/myconfig";

    String LOGOUT = "/user/logout";

    String UPDATE_USER = "/user/update";
}
