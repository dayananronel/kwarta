package com.kwarta.ph.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("user_type")
    @Expose
    String user_type;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_type() {
        return user_type;
    }
}
