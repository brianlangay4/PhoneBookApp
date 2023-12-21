package com.builtin.phonebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> contactList;
    private Context context;

    public ContactAdapter(ArrayList<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.contactNameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
        holder.contactDetailsTextView.setText(contact.getRelationship()); // Update with any relevant contact details

        // Set the first and last name initials as the icon
        String initials = contact.getFirstName().substring(0, 1) + contact.getLastName().substring(0, 1);
        holder.contactIconTextView.setText(initials);

        // Set an onClick listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new activity to display full contact information
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra("contact", contact);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTextView;
        TextView contactDetailsTextView;
        TextView contactIconTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactNameTextView = itemView.findViewById(R.id.contactNameTextView);
            contactDetailsTextView = itemView.findViewById(R.id.contactDetailsTextView);
            contactIconTextView = itemView.findViewById(R.id.contactIconTextView);
        }
    }
}
