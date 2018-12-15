package com.example.nashitaabd.a1dtest1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Blacklist extends AppCompatActivity {

    String TAG = "hey";
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference n;

    Button add_reject;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blacklist);
        Log.i(TAG, "hey onCreate() for Blacklist is invoked");

        //final DatabaseReference rejected = mFirebaseDatabase.getInstance().getReference("Rejected");
        final RejectedUsersAdapter rejectadapter = new RejectedUsersAdapter(this, MainActivity.rejected_persons);


        final ListView listViewReject = (ListView) findViewById(R.id.list_view_reject);
        listViewReject.setAdapter(rejectadapter);

//        add_reject = findViewById(R.id.add_reject);
//
//        add_reject.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rejected.child("Bertha").child("ID").setValue("1003045");
//            }
//        });
//        listViewReject.setAdapter(rejectadapter);

//        rejected.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Log.i(TAG, "there is a new reject");
//                String name = dataSnapshot.getKey();
//                Log.i(TAG, "MainActivity.reject.contains(name) is "+MainActivity.reject.contains(name));
//                if (MainActivity.reject.contains(name)==false ) {
//
//                    String id = dataSnapshot.child("ID").getValue(String.class);
//                    MainActivity.reject.add(name);
//                    rejected_info newUser = new rejected_info(name, id);
//                    MainActivity.rejected_persons.add(newUser);
//                    Log.i(TAG, "rejected_persons are" + Arrays.toString(MainActivity.reject.toArray()));
//                    listViewReject.setAdapter(rejectadapter);
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



    }
    @Override
    protected void onResume () {
        super.onResume();
        Log.i(TAG, "hey onResume//() for Blacklist is invoked");

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Blacklist.this, R.layout.list_item, MainActivity.reject);
//        ListView list = (ListView) findViewById(R.id.list_view_reject);
//        list.setAdapter(adapter);
    }
}
