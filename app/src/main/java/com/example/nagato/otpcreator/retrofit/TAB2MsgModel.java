package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TAB2MsgModel {

    @SerializedName("start") int Start;
    @SerializedName("limit") int Limit;
    @SerializedName("total") int Total;
    @SerializedName("messages") ArrayList<TAB2DataModel> Messages;
    @SerializedName("status") String Msg_Status;

    public TAB2MsgModel(int Start, int Limit, int Total, ArrayList<TAB2DataModel> Messages, String Msg_Status)
    {
        this.Start = Start;
        this.Limit = Limit;
        this.Total = Total;
        this.Messages = Messages;
        this.Msg_Status = Msg_Status;
    }

    public int getStart() {
        return Start;
    }

    public int getLimit() {
        return Limit;
    }

    public int getTotal() {
        return Total;
    }

    public ArrayList<TAB2DataModel> getMessages() {
        return Messages;
    }

    public String getMsg_Status() {
        return Msg_Status;
    }
}
