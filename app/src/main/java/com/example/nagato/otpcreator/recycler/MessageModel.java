package com.example.nagato.otpcreator.recycler;

public class MessageModel {

    private Long MSG_Name;
    private String TimeStamp, OTP;

    public MessageModel(Long MSG_Name, String TimeStamp, String OTP)
    {
        this.MSG_Name = MSG_Name;
        this.TimeStamp = TimeStamp;
        this.OTP = OTP;
    }

    public Long getMSG_Name() {
        return MSG_Name;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public String getOTP() {
        return OTP;
    }
}
