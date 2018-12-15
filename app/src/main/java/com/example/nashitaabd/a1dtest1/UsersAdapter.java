package com.example.nashitaabd.a1dtest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<approved_info> {
    public UsersAdapter(Context context, ArrayList<approved_info> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        approved_info approved_person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.approved_person, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text_name);
        TextView tvHome = (TextView) convertView.findViewById(R.id.text_id);
        TextView tvType = (TextView) convertView.findViewById(R.id.text_type);
        // Populate the data into the template view using the data object
        tvName.setText(approved_person.name);
        tvHome.setText(approved_person.id);
        tvType.setText(approved_person.type);
        // Return the completed view to render on screen
        return convertView;
    }
}