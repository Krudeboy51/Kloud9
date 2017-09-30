package com.example.krudeboy.kloud9.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Krudeboy on 9/29/2017.
 */

public class UserModel {

    @SerializedName("_id")
    String id;

    @SerializedName("email")
    String email;

    String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
