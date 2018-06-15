package com.example.nagato.otpcreator.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {

    @SerializedName("balance") int Balance;
    @SerializedName("batch_id") int Batch_ID;
    @SerializedName("cost") int Cost;
    @SerializedName("num_messages") int Num_Messages;
    @SerializedName("message") MsgModel Response_Message;
    @SerializedName("receipt_url") String Receipt_URL;
    @SerializedName("custom") String Custom;
    @SerializedName("messages") ArrayList<ReceipientModel> Receipient_Info;
    @SerializedName("status") String Status;
    @SerializedName("errors") ArrayList<ErrorModel> Response_Errors;

    public ResponseModel (int Balance, int Batch_ID, int Cost, int Num_Messages,
                          MsgModel Response_Message, String Receipt_URL, String Custom,
                          ArrayList<ReceipientModel> Recipient_Info, String Status, ArrayList<ErrorModel> Response_Errors)
    {
        this.Response_Message = Response_Message;
        this.Status = Status;
        this.Batch_ID = Batch_ID;
        this.Balance = Balance;
        this.Cost = Cost;
        this.Custom = Custom;
        this.Num_Messages = Num_Messages;
        this.Receipient_Info = Recipient_Info;
        this.Receipt_URL = Receipt_URL;
        this.Response_Errors = Response_Errors;
    }

    public String getStatus() {
        return Status;
    }

    public int getBatch_ID() {
        return Batch_ID;
    }

    public int getBalance() {
        return Balance;
    }

    public int getCost() {
        return Cost;
    }

    public int getNum_Messages() {
        return Num_Messages;
    }

    public ArrayList<ReceipientModel> getReceipient_Info() {
        return Receipient_Info;
    }

    public MsgModel getResponse_Message() {
        return Response_Message;
    }

    public String getCustom() {
        return Custom;
    }

    public String getReceipt_URL() {
        return Receipt_URL;
    }

    public ArrayList<ErrorModel> getResponse_Errors() {
        return Response_Errors;
    }
}
