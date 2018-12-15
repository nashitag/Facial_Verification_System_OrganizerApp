package com.example.nashitaabd.a1dtest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CompleteManualCheck extends AppCompatActivity {
    TextView textView_name;

    Button buttonYES;
    Button buttonNO;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    ImageView image;


    String ID_new;
    String type;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_manual_check);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra(ManualCheck_Overriden.NAME_KEY);
        final String id_new = intent.getStringExtra(ManualCheck_Overriden.WAIT_ID_KEY);

        //get ref
        textView_name = findViewById(R.id.text_view);
        TextView textView_id = findViewById(R.id.text_id);
        buttonYES = findViewById(R.id.button_yes);
        buttonNO = findViewById(R.id.button_no);
        image = findViewById(R.id.image);


        textView_name.setText(name);
        textView_id.setText(id_new);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Awaiting Manual Approval");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                String d = dataSnapshot.getKey();
                if (d.equals("default")==false) {
                    String link = dataSnapshot.child(id_new).child("reference photo").getValue(String.class);
                    type = dataSnapshot.child(id_new).child("type").getValue(String.class);
                    //ID_new = dataSnapshot.child(name).child("ID").getValue(String.class);
                    //link.replace("","");
                    Log.i("hey", "link is " + link);
                    //link = "\""+link.substring(0,-2)+"\"" + link.substring(-1,-1);
                    //Log.i("hey", "link is "+link);
                    //Log.i("hey", "name is "+name);
                    //Picasso.get().load(link).into(image);
                    link = link.replaceAll("^\"|\"$", "");
                    Log.i("hey", "image is displayed");
                    Log.i("hey", "link is " + link);
                    Glide.with(CompleteManualCheck.this).load(link).dontAnimate().into(image);
                }} catch(NullPointerException ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("firebaseerror", databaseError.toString());
            }

        });


        ///IMAGE VIEW///




        buttonYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hey", "Verify is pressed");
                myRef.child(id_new).removeValue();
                Log.i("hey", "value is removed");
                MainActivity.manually_approved.add(name);
                manuallyapproved_info newUser = new manuallyapproved_info(name, id_new, type);
                MainActivity.manuallyapproved_persons.add(newUser);

                MainActivity.wait_approval.remove(name);
                MainActivity.wait_id.remove(id_new);
                String message = name+" has been verified.";

                Toast.makeText(CompleteManualCheck.this, message ,Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(CompleteManualCheck.this, ManualCheck_Overriden.class);
                startActivity(intent1);
            }
        });

        buttonNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hey", "Reject is pressed");
                myRef.child(id_new).removeValue();
                Log.i("hey", "value is removed");
                MainActivity.reject.add(name);
                rejected_info newUser = new rejected_info(name, id_new, type);
                MainActivity.rejected_persons.add(newUser);

                MainActivity.wait_approval.remove(name);
                MainActivity.wait_id.remove(id_new);
                String message = name+" has been rejected.";
                Toast.makeText(CompleteManualCheck.this, message ,Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(CompleteManualCheck.this, ManualCheck_Overriden.class);
                startActivity(intent1);
            }
        });
    }
}
