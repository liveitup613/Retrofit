package com.todo.vidyanandmishra.retofitexample.models;

/**
 * Created by vidyanandmishra on 13/12/16.
 *
 * NOTE:
 * Change response POJO as per your JSON response
 */

public class SuccessResponseModel {

    private boolean ok;
    private Data response;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Data getResponse() {
        return response;
    }

    public void setResponse(Data response) {
        this.response = response;
    }

    public class Data {

        private String sessionToken;

        public String getSessionToken() {
            return sessionToken;
        }

        public void setSessionToken(String sessionToken) {
            this.sessionToken = sessionToken;
        }
    }
}
