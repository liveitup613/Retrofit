package com.todo.vidyanandmishra.retofitexample.models;

/**
 * Created by vidyanandmishra on 13/12/16.
 *
 * NOTE:
 * Change this custom error POJO as per your JSON response
 */

public class ErrorResponseModel {

    private boolean ok;
    private Error error;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public class Error {
        private int code;
        private String reason;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
