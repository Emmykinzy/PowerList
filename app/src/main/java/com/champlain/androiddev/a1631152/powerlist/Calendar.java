package com.champlain.androiddev.a1631152.powerlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.Date;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;
import com.champlain.androiddev.a1631152.powerlist.Models.User;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final int UserId = getIntent().getIntExtra("user_id", 0);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(null);
        final CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendar.setFirstDayOfWeek(1);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        instantiate();

        // define a listener to receive callbacks when certain events happen.
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(java.util.Date dateClicked) {
                Toast toast = Toast.makeText(getApplicationContext(), ""+dateClicked, Toast.LENGTH_SHORT);
                toast.show();

            }

            @Override
            public void onMonthScroll(java.util.Date firstDayOfNewMonth) {

            }
        });


        ImageButton addTask = findViewById(R.id.addTaskBtn);
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

    public void instantiate()
    {

        final int UserId = getIntent().getIntExtra("user_id", 0);
        ArrayList<Date> date = refresh();
        for(int x = 0; x<date.size(); x++)
        {
            Date d = date.get(x);
            if(d.getUserId() == UserId)
            {
               String myDate = Integer.toString(d.getDate());
                char[] carr = myDate.toCharArray();

                char a = carr[6];
                char b = carr[7];
                char tmp = carr[0];
                carr[0] = carr[4];
                carr[6] = tmp;
                tmp = carr[1];
                carr[1] = carr[5];
                carr[7] = tmp;
                carr[4] = carr[2];
                carr[5] = carr[3];
                carr[2] = a;
                carr[3] = b;

                myDate = new String(carr);

                myDate = myDate.substring(0, 4) + "/" + myDate.substring(4, myDate.length());
                myDate = myDate.substring(0, 7) + "/" + myDate.substring(7, myDate.length());
                myDate = myDate+" 8:00:00";
                CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                java.util.Date date1 = null;
                try {
                    date1 = sdf.parse(myDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long millis = date1.getTime();

                if(d.isCompleted())
                {
                    Event ev1 = new Event(Color.GREEN, millis);
                    compactCalendar.addEvent(ev1);
                }
                else
                {
                    Event ev1 = new Event(Color.RED, millis);
                    compactCalendar.addEvent(ev1);
                }

            }
        }
    }
    public ArrayList<Date> refresh()
    {
        ArrayList<Date> date;
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.getDates();
        date = manager.getDate_list();

        return date;
    }
}
