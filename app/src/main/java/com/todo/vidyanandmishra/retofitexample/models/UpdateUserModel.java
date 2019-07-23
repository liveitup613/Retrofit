package com.todo.vidyanandmishra.retofitexample.models;

/**
 * Created by vidyanandmishra on 13/12/16.
 *
 * NOTE:
 * This is just dummy POJO class with dummy fields.
 * Change as per you requirement.
 */

public class UpdateUserModel {

    private String userName;
    private String phoneNumber;
    private String houseNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
