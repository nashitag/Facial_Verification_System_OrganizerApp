package com.example.nashitaabd.a1dtest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RejectedUsersAdapter extends ArrayAdapter<rejected_info> {
    public RejectedUsersAdapter(Context context, ArrayList<rejected_info> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        rejected_info rejected_person = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rejected_person, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text_name_reject);
        TextView tvID = (TextView) convertView.findViewById(R.id.text_id_reject);
        TextView tvType = (TextView) convertView.findViewById(R.id.text_type_reject);
        // Populate the data into the template view using the data object
        assert rejected_person != null;
        tvName.setText(rejected_person.name);
        tvID.setText(rejected_person.id);
        tvType.setText(rejected_person.type);
        // Return the completed view to render on screen
        return convertView;
    }
}