package com.example.nashitaabd.a1dtest1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Overview extends AppCompatActivity {

    TextView alumni_no;
    TextView students_no;
    TextView staff_no;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        alumni_no = findViewById(R.id.alumni_no);
        students_no = findViewById(R.id.students_no);
        staff_no = findViewById(R.id.staff_no);





        String a = Integer.toString(MainActivity.alumni_count);
        String sd = Integer.toString(MainActivity.student_count);
        String st = Integer.toString(MainActivity.staff_count);



        alumni_no.setText(a);
        students_no.setText(sd);
        staff_no.setText(st);

    }
}
