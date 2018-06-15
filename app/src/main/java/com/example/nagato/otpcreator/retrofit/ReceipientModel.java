package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

public class ReceipientModel {

    @SerializedName("id") String Receipient_ID;
    @SerializedName("recipient") long Receipient;

    public  ReceipientModel(String Receipient_ID, long Receipient)
    {
        this.Receipient_ID = Receipient_ID;
        this.Receipient = Receipient;
    }

    public String getReceipient_ID() {
        return Receipient_ID;
    }

    public long getReceipient() {
        return Receipient;
    }
}
