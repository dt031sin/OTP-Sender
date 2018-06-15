package com.example.nagato.otpcreator.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Client {

    @FormUrlEncoded
    @POST("send/")
    Call<ResponseModel> send(@Field("apikey") String API_Key,
                              @Field("message") String Message,
                              @Field("sender") String Sender,
                              @Field("numbers") String Numbers);

    @FormUrlEncoded
    @POST("get_history_api/")
    Call<TAB2MsgModel> receive(@Field("apikey") String API_Key);
}
