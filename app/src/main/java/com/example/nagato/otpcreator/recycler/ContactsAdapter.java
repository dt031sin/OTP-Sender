package com.example.nagato.otpcreator.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nagato.otpcreator.InfoActivity;
import com.example.nagato.otpcreator.R;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {

    private ArrayList<ContactsModel> dataSet;
    private Context context;

    public ContactsAdapter(ArrayList<ContactsModel> data, Context context)
    {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_contact_card, parent, false);

        ContactsViewHolder contacts_viewHolder = new ContactsViewHolder(view);
        return contacts_viewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactsViewHolder holder, final int position) {

        final String Name = dataSet.get(position).getFirstName() + " " + dataSet.get(position).getLastName();
        holder.name.setText(Name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, InfoActivity.class);
                i.putExtra("name", Name);
                i.putExtra("phone", dataSet.get(position).getPhoneNumber());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }
}
