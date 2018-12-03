package com.champlain.androiddev.a1631152.powerlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;
import com.champlain.androiddev.a1631152.powerlist.Models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        final int userId = getIntent().getIntExtra("id", 0);

        Button save = findViewById(R.id.saveBtn);
        Button back = findViewById(R.id.backBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date d = new Date();

                SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat month = new SimpleDateFormat("MM");
                SimpleDateFormat year = new SimpleDateFormat("yyyy");

                String date = day.format(d);

                int DateID = Integer.parseInt(date.toString());



                CheckBox ch1 = findViewById(R.id.Check1);
                CheckBox ch2 = findViewById(R.id.Check2);
                CheckBox ch3 = findViewById(R.id.Check3);
                CheckBox ch4 = findViewById(R.id.Check4);
                CheckBox ch5 = findViewById(R.id.Check5);

                EditText d1 = findViewById(R.id.Task1);
                EditText d2 = findViewById(R.id.Task2);
                EditText d3 = findViewById(R.id.Task3);
                EditText d4 = findViewById(R.id.Task4);
                EditText d5 = findViewById(R.id.Task5);

                d1.getText().length();
                if(d1.getText().length() < 1 || d2.getText().length() < 1  || d3.getText().length() < 1  || d4.getText().length() < 1  || d5.getText().length() < 1 )
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please add 5 task before saving!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    Task t = new Task(userId, DateID, ch1.isChecked(), d1.getText().toString(), ch2.isChecked(), d2.getText().toString(), ch3.isChecked(), d3.getText().toString(), ch4.isChecked(), d4.getText().toString(), ch5.isChecked(), d5.getText().toString());
                    addTask(t);
                    finish();
                }
             }
            }
        );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        }
    private Task addTask(Task t)
    {
        //Contact createdContact = DBManager.addContact(c);

        DBSQLiteManager dbslm = new DBSQLiteManager(this);
        Task createdTask = dbslm.addTask(t);

        return createdTask;
    }

}
