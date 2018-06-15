package com.example.nagato.otpcreator.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nagato.otpcreator.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<MessageModel> dataSet;
    private Context context;

    public MessageAdapter(ArrayList<MessageModel> data, Context context)
    {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_message_card, parent, false);

        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {

        String Msg_Name = dataSet.get(position).getMSG_Name().toString();
        holder.msg_name.setText(Msg_Name);
        holder.timeStamp.setText(dataSet.get(position).getTimeStamp());
        holder.otp.setText(dataSet.get(position).getOTP());

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }
}
