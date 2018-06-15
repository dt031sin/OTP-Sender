package com.example.nagato.otpcreator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nagato.otpcreator.retrofit.Client;
import com.example.nagato.otpcreator.retrofit.ErrorModel;
import com.example.nagato.otpcreator.retrofit.ResponseModel;
import com.example.nagato.otpcreator.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    String Name, Phone, Sender, API_KEY;
    TextView name, phone, msg;
    Button send;
    ResponseModel responseModel;

    Snackbar snackbar;
    ConstraintLayout constraintLayout;

    Client client;
    Call<ResponseModel> listCall;

    AlertDialog.Builder builder;
    View DialogView;
    AlertDialog msgDialog;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //get intent and extras
        Name = getIntent().getStringExtra("name");
        Phone = getIntent().getStringExtra("phone");

        // Initialize and set the Text Views
        name = findViewById(R.id.info_name);
        name.setText(Name);
        phone = findViewById(R.id.info_phone);
        phone.setText(Phone);

        client = ServiceGenerator.createService(Client.class); // create retrofit service

        API_KEY = "/XC4jgFfqig-yi2tN6o9gGYgGMb4GTr4F2IwksgUow";// custom api key
        Sender = "TXTLCL"; // Sender signature specific of TextLocal service

        //send button OnClick
        send = findViewById(R.id.info_button);
        constraintLayout = findViewById(R.id.parent_anchor);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(constraintLayout);
            }
        });
    }

    // method to show the send message dialog box
    private void showPopup(final View anchorView) {

        layoutInflater = LayoutInflater.from(InfoActivity.this);
        DialogView = layoutInflater.inflate(
                R.layout.msg_dialog, null);
        builder = new AlertDialog.Builder(InfoActivity.this);
        msgDialog = builder.create();
        msgDialog.setView(DialogView);
        msgDialog.setCancelable(false);

        msg = DialogView.findViewById(R.id.msg);
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000); // random OTP generator
        final String message = "Hi.  Your  OTP  is: " + n;
        msg.setText(message);

        // send button onClick
        DialogView.findViewById(R.id.msg_send).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listCall = client.send(API_KEY, message, Sender, Phone); //retrofit call
                listCall.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                        responseModel = response.body();// get response onto the Response Model

                        if(responseModel == null)
                        {
                            snackbar = Snackbar.make(anchorView, "Empty Response: Msg not sent", Snackbar.LENGTH_LONG)
                                    .setActionTextColor(Color.CYAN);
                        }
                        else
                        {
                            if(responseModel.getStatus().equals("failure"))
                            {
                                ArrayList<ErrorModel> error_msg = responseModel.getResponse_Errors();
                                snackbar = Snackbar.make(anchorView, error_msg.get(0).getError_msg(), Snackbar.LENGTH_LONG)
                                        .setActionTextColor(Color.CYAN);
                            }
                            else if (responseModel.getStatus().equals("success"))
                            {
                                snackbar = Snackbar.make(anchorView, responseModel.getStatus() + ", Balance Left = " +
                                        responseModel.getBalance(), Snackbar.LENGTH_LONG)
                                        .setActionTextColor(Color.CYAN);
                            }
                        }

                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.DKGRAY);
                        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.YELLOW);
                        msgDialog.dismiss();
                        snackbar.show();

                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        msgDialog.dismiss();
                    }
                });
            }

        });

        // end button onClick
        DialogView.findViewById(R.id.msg_cancel).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                msgDialog.dismiss();
            }
        });

        msgDialog.show();
    }
}
