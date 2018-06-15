package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

public class MsgModel {

    @SerializedName("num_parts") int Num_Parts;
    @SerializedName("sender") String Sender;
    @SerializedName("content") String Content;

    public  MsgModel(int Num_Parts, String Sender, String Content)
    {
        this.Num_Parts = Num_Parts;
        this.Sender = Sender;
        this.Content = Content;
    }

    public int getNum_Parts() {
        return Num_Parts;
    }

    public String getSender() {
        return Sender;
    }

    public String getContent() {
        return Content;
    }
}
