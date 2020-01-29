package com.kwarta.ph.utilities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericResponse {

    @SerializedName("data")
    String data;

    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;


    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
