package com.example.nashitaabd.a1dtest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ManualCheck_Overriden extends AppCompatActivity {
    TextView textPersonCheck;
    final static String NAME_KEY = "Name of Person to Approve";
    final static String WAIT_ID_KEY = "ID of Person to Approve";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manualcheck_override);
        Log.i("hey", "onCreate for manualCheck is invoked");

        ListView listview = (ListView) findViewById(R.id.list_view_manual);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManualCheck_Overriden.this, R.layout.list_item, MainActivity.wait_approval);
        listview.setAdapter(adapter);
        Log.i("hey", "list is created");



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //when we click on some item on the list, it will call this method
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManualCheck_Overriden.this, CompleteManualCheck.class);
                intent.putExtra(NAME_KEY, MainActivity.wait_approval.get(position));
                intent.putExtra(WAIT_ID_KEY, MainActivity.wait_id.get(position));
                startActivity(intent);
            }
        });

        if (MainActivity.wait_approval.isEmpty()==true){
            Intent intent_back = new Intent(ManualCheck_Overriden.this, MainActivity.class);
            startActivity(intent_back);
        }


    }
}
