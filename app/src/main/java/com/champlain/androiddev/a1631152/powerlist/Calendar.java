package com.champlain.androiddev.a1631152.powerlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Calendar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ImageButton addTask = findViewById(R.id.addTaskBtn);
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Tasks.class);
                startActivityForResult(i, 1);
            }
        });

        ImageButton add = findViewById(R.id.addTaskBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Tasks.class);

                startActivity(i);
            }
        }
        );
    }
}
