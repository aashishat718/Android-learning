package com.example.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.DetailsActivity;
import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_row,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.contactName.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView contactName;
        public TextView phoneNumber;
        public ImageView iconButton;
        //private final int REQUEST_CODE=2;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            itemView.setOnClickListener(this);
            contactName = itemView.findViewById(R.id.name_id);
            phoneNumber = itemView.findViewById(R.id.phone_id);
            iconButton = itemView.findViewById(R.id.iconButton_id);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Contact contact = contactList.get(position);

            switch (view.getId())
            {
                case R.id.iconButton_id:
                    Log.d("Icon clicked", "onClick: "+ contact.getName());
                    break;
//                case R.id.recyclerView_id:
//                    break;
            }
            Log.d("Clicked", "onClick: " + position);
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("phone",contact.getPhoneNumber());
            view.getContext().startActivity(intent);

            


        }
    }
}
