package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TAB2DataModel {

    @SerializedName("id") String ID;
    @SerializedName("number") long Number;
    @SerializedName("content") String Content;
    @SerializedName("datetime") String DateTime;
    @SerializedName("sender") String Sender;
    @SerializedName("status") String Status;
    @SerializedName("customID") String CustomID;
    @SerializedName("links") ArrayList<String> Links;

    public TAB2DataModel (String ID, long Number, String Content, String DateTime, String Sender,
                          String Status, String CustomID, ArrayList<String> Links)
    {
        this.ID = ID;
        this.Number = Number;
        this.Content = Content;
        this.DateTime= DateTime;
        this.Sender = Sender;
        this.Status = Status;
        this.CustomID = CustomID;
        this.Links = Links;
    }

    public String getID() {
        return ID;
    }

    public long getNumber() {
        return Number;
    }

    public String getContent() {
        return Content;
    }

    public String getDateTime() {
        return DateTime;
    }

    public String getSender() {
        return Sender;
    }

    public String getStatus() {
        return Status;
    }

    public String getCustomID() {
        return CustomID;
    }

    public ArrayList<String> getLinks() {
        return Links;
    }
}
