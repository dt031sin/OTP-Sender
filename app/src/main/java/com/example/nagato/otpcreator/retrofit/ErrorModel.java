package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    @SerializedName("code") int error_code;
    @SerializedName("message") String error_msg;

    public  ErrorModel(int error_code, String error_msg)
    {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public String getError_msg() {
        return error_msg;
    }

    public int getError_code() {
        return error_code;
    }
}
