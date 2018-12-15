package com.example.nashitaabd.a1dtest1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


public class Registered extends AppCompatActivity {

    String TAG = "hey";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private TextView text_view_data;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);
        Log.i("hey", "onCreate is invoked for Registered");
        final DatabaseReference myRef = mFirebaseDatabase.getInstance().getReference("Approved");


        text_view_data = findViewById(R.id.text_view_data);

        final UsersAdapter adapter = new UsersAdapter(this, MainActivity.approved_persons);
        final ManualUsersAdapter manualadapter = new ManualUsersAdapter(this, MainActivity.manuallyapproved_persons);




        final ListView listView = (ListView) findViewById(R.id.list_view);
        final ListView MANUALlistView = (ListView)findViewById(R.id.list_view_manual);
        listView.setAdapter(adapter);
        Log.i(TAG, "adapter has changed");
        MANUALlistView.setAdapter(manualadapter);
        Log.i(TAG, Arrays.toString(MainActivity.manually_approved.toArray()));

        Button add_approved = findViewById(R.id.add_approved);
        add_approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("default").child("ID").setValue("1000000");
            }
        });



        //mListView = (ListView)findViewById(R.id.listview);






        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String id = dataSnapshot.getKey();
                if(id.equals("default")==false) {
                    String name = dataSnapshot.child("name").getValue(String.class);


                    if (MainActivity.names.contains(name) == false) {
                        MainActivity.names.add(name);
                        String id_new = dataSnapshot.child("ID").getValue(String.class);
                        String type = dataSnapshot.child("type").getValue(String.class);
                        approved_info newUser = new approved_info(name, id_new, type);
                        //adapter.add(newUser);
                        MainActivity.approved_persons.add(newUser);
                        listView.setAdapter(adapter);
                        Log.i(TAG, "names list is " + Arrays.toString(MainActivity.names.toArray()));

                        if(newUser.type.equals("Staff")){
                            MainActivity.staff_count+=1;
                        }
                        if(newUser.type.equals("Student")){
                            MainActivity.student_count+=1;
                            //MainActivity.students.add(name);
                        }
                        if(newUser.type.equals("Alumni")){
                            MainActivity.alumni_count+=1;
                        }


                    }
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








//                if (id_new.equals(MainActivity.id)==false ) {
//                    MainActivity.id=id_new;
//                    String n = dataSnapshot.child("Name").getValue(String.class);
//                    MainActivity.names.add(n);
//                    approved_info newUser = new approved_info(n, id_new, type);
//                    adapter.add(newUser);
//                    MainActivity.approved_persons.add(newUser);
//                    listView.setAdapter(adapter);
//                    Log.i(TAG, "adapter has changed");
////                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.names);
////                    ListView list = (ListView) findViewById(R.id.list_view);
////                    list.setAdapter(adapter);
//
//
//                    if(type.equals("Staff")){
//                        MainActivity.staff_count+=1;
//                    }
//                    else if(type.equals("Student")){
//                        MainActivity.student_count+=1;
//                    }
//                    else if(type.equals("Alumni")){
//                        MainActivity.alumni_count+=1;
//                    }
//
//
//                    text_view_data.setText("Bertha is present");
//                }



//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("firebaseerror", databaseError.toString());
//            }
//        });

//        ArrayAdapter<String> adapter_formanual = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.manually_approved);
//        ListView list_manual = (ListView) findViewById(R.id.list_view_manual);
//        list_manual.setAdapter(adapter_formanual);



    }


        //populateListView();


    protected void onStart() {
        super.onStart();
        Log.i("hey", "onStart is invoked for Registered");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.names);
//        ListView list = (ListView) findViewById(R.id.list_view);
//        list.setAdapter(adapter);


//        ArrayAdapter<String> adapter_formanual = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.manually_approved);
//        ListView list_manual = (ListView) findViewById(R.id.list_view_manual);
//        list_manual.setAdapter(adapter_formanual);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("hey", "onResume is invoked for Registered");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.names);
//        ListView list = (ListView) findViewById(R.id.list_view);
//        list.setAdapter(adapter);

//        ArrayAdapter<String> adapter_formanual = new ArrayAdapter<String>(Registered.this, R.layout.list_item, MainActivity.manually_approved);
//        ListView list_manual = (ListView) findViewById(R.id.list_view_manual);
//        list_manual.setAdapter(adapter_formanual);

    }

/*
    private void populateListView() {
        //create list of items
        //array of strings

        String[] my_items = {"Blue", "Green", "Purple", "Red"};

        //build the adapter
        //convert items into appropriate elements
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, my_items);


        //context, items to be displayed, layout to use

        //configure the list view
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }
*/
    //private void showData(DataSnapshot dataSnapshot) {
       // for (DataSnapshot ds : dataSnapshot.getChildren()){

            //ui.setId(ds.getValue(UserInfo.class).getId());
            //ui.setId(ds.child("name").toString());


            //Log.d(TAG, "id is "+)
            //ui.setName(ds.child("Caleb").getValue(UserInfo.class).getName());
            //ui.setPhoto(ds.child("Caleb").getValue(UserInfo.class).getPhoto());


        //}
    }
