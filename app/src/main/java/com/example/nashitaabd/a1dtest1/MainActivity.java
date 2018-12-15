package com.example.nashitaabd.a1dtest1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final static ArrayList<String> names = new ArrayList<>();
    final static ArrayList<String> reject = new ArrayList<>();
    final static ArrayList<String> wait_approval = new ArrayList<>();
    final static ArrayList<String> wait_id = new ArrayList<>();
    final static ArrayList<String> manually_approved = new ArrayList<>();
    final static ArrayList<String> approved_id = new ArrayList<>();

    final static ArrayList<String> students = new ArrayList<>();

    static int alumni_count = 0;
    static int student_count = 0;
    static int staff_count = 0;


    //static String id = "1000000";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;


    public static final String LIST_KEY = "LIST_KEY";
    public static final String REJECTLIST_KEY = "REJECT_LIST";
    public static final String WAITLIST_KEY = "WAITING APPROVAL LIST";
    public static final String MANUAL_KEY = "MANUALLY APPROVED";
    public static final String APPROVED_ID  = "ID OF APPROVED";
    public static final String WAIT_ID = "ID OF WAITING";

    public static final String STUDENTS = "List of students";


    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String ALUMNI_KEY = "COUNT OF ALUMNI";
    public static final String STUDENT_KEY = "COUNT OF STUDENT";
    public static final String STAFF_KEY = "COUNT OF STAFF";
    SharedPreferences mPref;

    private Button update_firebase;

    final static ArrayList<approved_info> approved_persons = new ArrayList<approved_info>();
    final static ArrayList<manuallyapproved_info> manuallyapproved_persons = new ArrayList<manuallyapproved_info>();
    final static ArrayList<rejected_info> rejected_persons = new ArrayList<rejected_info>();


    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }


    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    //**************************ON CREATE*********************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //student_count = 0;
        //alumni_count = 0;
        //staff_count = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i("hey", "onCreate is invoked for Main Activity");

        getArrayList(LIST_KEY);
        getArrayList(REJECTLIST_KEY);
        getArrayList(WAITLIST_KEY);
        getArrayList(MANUAL_KEY);
        getArrayList(APPROVED_ID);
        getArrayList(WAIT_ID);
        getArrayList(STUDENTS);

        mPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        alumni_count = mPref.getInt(ALUMNI_KEY, 0);
        student_count = mPref.getInt(STUDENT_KEY, 0);
        staff_count = mPref.getInt(STAFF_KEY, 0);

        update_firebase = findViewById(R.id.update_firebase);

        update_firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference upload = mFirebaseDatabase.getInstance().getReference("Awaiting Manual Approval");
                upload.child("default").child("ID").setValue("1000000");
                upload.child("1003444").child("name").setValue("Benjamin Goh");
                upload.child("1003444").child("reference photo").setValue("==");
                upload.child("1003444").child("ID").setValue("1003444");
                upload.child("1003444").child("type").setValue("Student");
                upload.child("1003000").child("name").setValue("Bertha Han");
                upload.child("1003000").child("reference photo").setValue("link");
                upload.child("1003000").child("ID").setValue("1003000");
                upload.child("1003000").child("type").setValue("Staff");

                Log.i("hey", "button is pressed");
            }
        });



// Create the adapter to convert the array to views

        final ImageView img_sutd = findViewById(R.id.sutd);
        final TextView welcome = findViewById(R.id.text_welcome);
        final TextView other = findViewById(R.id.othertext);
        final TextView other_info = findViewById(R.id.othertext_info);



        final Button button_reg = (Button)findViewById(R.id.button_reg);
        final Button overview = (Button)findViewById(R.id.button_ov);
        final Button button_bl = (Button)findViewById(R.id.button_bl);

        AnimationDrawable animationDrawable2 = (AnimationDrawable) button_bl.getBackground();
        animationDrawable2.setEnterFadeDuration(2000);
        animationDrawable2.setExitFadeDuration(5000);
        animationDrawable2.start();

        AnimationDrawable animationDrawable3 = (AnimationDrawable) overview.getBackground();
        animationDrawable3.setEnterFadeDuration(2000);
        animationDrawable3.setExitFadeDuration(5000);
        animationDrawable3.start();


        img_sutd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anime = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
                img_sutd.startAnimation(anime);
                welcome.animate().translationX(550).setDuration(1000);
                other.animate().translationY(-900).setDuration(1000);
                button_reg.animate().translationY(1000).setDuration(1000);
                button_bl.animate().translationY(1000).setDuration(1000);
                overview.animate().translationY(1000).setDuration(1000);

                other_info.animate().translationY(-900).setDuration(1000);


            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anime = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomout);
                img_sutd.startAnimation(anime);
                welcome.animate().translationX(-550).setDuration(1000);
                other.animate().translationY(900).setDuration(1000);
                button_reg.animate().translationY(-50).setDuration(1000);
                button_bl.animate().translationY(-50).setDuration(1000);
                overview.animate().translationY(-50).setDuration(1000);

                other_info.animate().translationY(900).setDuration(1000);



            }
        });

        AnimationDrawable animationDrawable1 = (AnimationDrawable) button_reg.getBackground();
        animationDrawable1.setEnterFadeDuration(2000);
        animationDrawable1.setExitFadeDuration(5000);
        animationDrawable1.start();

        //ImageView bck = findViewById(R.id.imageView3);

        //bck.animate().translationY(-400).setDuration(1000);

        FrameLayout linearLayout = (FrameLayout) findViewById(R.id.linear_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);

        animationDrawable.start();

        button_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Blacklist.class);
                startActivity(intent);
            }
        });
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registered.class);
                startActivity(intent);
            }
        });
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Overview.class);
                startActivity(intent);
            }
        });





        DatabaseReference awaiting_approval = mFirebaseDatabase.getInstance().getReference("Awaiting Manual Approval");

        awaiting_approval.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String id = dataSnapshot.getKey();

                if(id.equals("default")==false) {

                    String name = dataSnapshot.child("name").getValue(String.class);
                    Log.i("hey", "name is " + name + id);

                    if (wait_approval.contains(name) == false) {
                        wait_approval.add(name);
                        wait_id.add(id);
                    }
                    Intent intent = new Intent(MainActivity.this, ManualCheck_Overriden.class);
                    startActivity(intent);
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




//        DatabaseReference approved = mFirebaseDatabase.getInstance().getReference("Approved");
//        approved.child("ID").setValue("1000000");
//        approved.child("Name").setValue("null");
//        approved.child("Actual Photo").setValue("null");
//        approved.child("Reference Photo").setValue("null");
//        approved.child("Phone").setValue("1");
//        approved.child("Approval").setValue("false");
//        approved.child("Awaiting Manual Approval").setValue("false");
//        approved.child("Rejected").setValue("false");
//        approved.child("ID Validity").setValue("false");
//        approved.child("Type").setValue("null");





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    //**************************ON CREATE*********************************


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.registered) {
            Intent intent = new Intent(MainActivity.this, Registered.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.blacklist) {
            Intent intent = new Intent(MainActivity.this, Blacklist.class);
            startActivity(intent);
        } else if (id == R.id.overview) {
            Intent intent = new Intent(MainActivity.this, Overview.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("hey", "onStart is invoked for Main Activity");
    }
    protected void onResume() {
        super.onResume();
        Log.i("hey", "onResume is invoked for Main Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("hey", "onStop is invoked for Main Activity");
        saveArrayList(names, LIST_KEY);
        saveArrayList(reject, REJECTLIST_KEY);
        saveArrayList(wait_approval, WAITLIST_KEY);
        saveArrayList(manually_approved, MANUAL_KEY);
        saveArrayList(approved_id, APPROVED_ID);
        saveArrayList(wait_id, WAIT_ID);
        saveArrayList(students, STUDENTS);


        SharedPreferences.Editor prefEditor = mPref.edit();
        prefEditor.putInt(ALUMNI_KEY, alumni_count);
        prefEditor.putInt(STUDENT_KEY, student_count);
        prefEditor.putInt(STAFF_KEY, staff_count);
        prefEditor.apply();
    }
}
