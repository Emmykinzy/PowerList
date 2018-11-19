package com.champlain.androiddev.a1631152.powerlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Calendar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ImageButton addTask = findViewById(R.id.addTaskBtn);
       final int UserId = getIntent().getIntExtra("user_id", 0);

        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), com.champlain.androiddev.a1631152.powerlist.addTask.class);
                startActivityForResult(i, 1);
            }
        });

        ImageButton add = findViewById(R.id.addTaskBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), com.champlain.androiddev.a1631152.powerlist.addTask.class);
                i.putExtra("id", UserId);
                startActivity(i);
            }
        }
        );
    }
}
