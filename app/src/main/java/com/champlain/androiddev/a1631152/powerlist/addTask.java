package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.Date;

public class addTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        final Intent intent = getIntent();

        final int userId = intent.getIntExtra("id", 0);
        if (intent.hasExtra("ch1"))
        {
            Button saveEdit = findViewById(R.id.saveBtn);
            Button backEdit = findViewById(R.id.backBtn);
            backEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            EditText t1 = findViewById(R.id.Task1);
            EditText t2 = findViewById(R.id.Task2);
            EditText t3 = findViewById(R.id.Task3);
            EditText t4 = findViewById(R.id.Task4);
            EditText t5 = findViewById(R.id.Task5);

            CheckBox c1 = findViewById(R.id.Check1);
            CheckBox c2 = findViewById(R.id.Check2);
            CheckBox c3 = findViewById(R.id.Check3);
            CheckBox c4 = findViewById(R.id.Check4);
            CheckBox c5 = findViewById(R.id.Check5);

            c1.setChecked(sTb(intent.getStringExtra("ch1")));
            c2.setChecked(sTb(intent.getStringExtra("ch2")));
            c3.setChecked(sTb(intent.getStringExtra("ch3")));
            c4.setChecked(sTb(intent.getStringExtra("ch4")));
            c5.setChecked(sTb(intent.getStringExtra("ch5")));

            t1.setText(intent.getStringExtra("d1"));
            t2.setText(intent.getStringExtra("d2"));
            t3.setText(intent.getStringExtra("d3"));
            t4.setText(intent.getStringExtra("d4"));
            t5.setText(intent.getStringExtra("d5"));

            saveEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int uId = intent.getIntExtra("uId", 0);
                    int dId = intent.getIntExtra("dId", 0);
                    EditText t1 = findViewById(R.id.Task1);
                    EditText t2 = findViewById(R.id.Task2);
                    EditText t3 = findViewById(R.id.Task3);
                    EditText t4 = findViewById(R.id.Task4);
                    EditText t5 = findViewById(R.id.Task5);

                    CheckBox c1 = findViewById(R.id.Check1);
                    CheckBox c2 = findViewById(R.id.Check2);
                    CheckBox c3 = findViewById(R.id.Check3);
                    CheckBox c4 = findViewById(R.id.Check4);
                    CheckBox c5 = findViewById(R.id.Check5);

                    String d1 = t1.getText().toString();
                    String d2 = t2.getText().toString();
                    String d3 = t3.getText().toString();
                    String d4 = t4.getText().toString();
                    String d5 = t5.getText().toString();

                    updateTasks(uId, dId, c1.isChecked(), c2.isChecked(), c3.isChecked(), c4.isChecked(), c5.isChecked(), d1, d2, d3, d4, d5);
                    finish();

                }
            });
        }
        else
        {

            Button save = findViewById(R.id.saveBtn);
            Button back = findViewById(R.id.backBtn);
            save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Date d = new Date();

              SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");

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
              if (d1.getText().length() < 1 || d2.getText().length() < 1 || d3.getText().length() < 1 || d4.getText().length() < 1 || d5.getText().length() < 1)
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
    }
    private Task addTask(Task t)
    {
        //Contact createdContact = DBManager.addContact(c);

        DBSQLiteManager dbslm = new DBSQLiteManager(this);
        Task createdTask = dbslm.addTask(t);

        return createdTask;
    }

    private boolean sTb(String sb)
    {
        String t = "true";
        if(sb.equals(t))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void updateTasks(int userId, int dateId, boolean c1, boolean c2, boolean c3, boolean c4, boolean c5, String d1, String d2, String d3, String d4, String d5)
    {
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.updateTasks(userId, dateId, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5);

    }


}
