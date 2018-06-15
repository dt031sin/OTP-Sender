package com.example.nagato.otpcreator.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nagato.otpcreator.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView  msg_name, timeStamp, otp;

    public MessageViewHolder(View itemView)
    {
        super(itemView);

        this.msg_name = itemView.findViewById(R.id.msg_name);
        this.timeStamp = itemView.findViewById(R.id.msg_timeStamp);
        this.otp = itemView.findViewById(R.id.msg_otp);

    }
}
