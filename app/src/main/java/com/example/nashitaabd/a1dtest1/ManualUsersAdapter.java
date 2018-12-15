package com.example.nashitaabd.a1dtest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ManualUsersAdapter extends ArrayAdapter<manuallyapproved_info> {
    public ManualUsersAdapter(Context context, ArrayList<manuallyapproved_info> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        manuallyapproved_info manapproved_person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.manuallyapproved_person, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text_name_manual);
        TextView tvID = (TextView) convertView.findViewById(R.id.text_id_manual);
        TextView tvType = (TextView) convertView.findViewById(R.id.text_type_manual);
        // Populate the data into the template view using the data object
        tvName.setText(manapproved_person.name);
        tvID.setText(manapproved_person.id);
        tvType.setText(manapproved_person.type);
        // Return the completed view to render on screen
        return convertView;
    }
}