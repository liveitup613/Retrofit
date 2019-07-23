package com.todo.vidyanandmishra.retofitexample.models;

/**
 * Created by vidyanandmishra on 13/12/16.
 */

public class LoginRequestModel {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
