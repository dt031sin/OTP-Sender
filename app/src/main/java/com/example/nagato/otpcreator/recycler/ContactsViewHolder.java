package com.example.nagato.otpcreator.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.nagato.otpcreator.R;

public class ContactsViewHolder extends RecyclerView.ViewHolder {

    public TextView name;

    public ContactsViewHolder(View itemView)
    {
        super(itemView);

        this.name = (TextView) itemView.findViewById(R.id.contact_name);

    }
}
