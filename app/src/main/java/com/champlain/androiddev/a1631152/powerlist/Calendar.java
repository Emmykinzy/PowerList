package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.Dates;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
                ArrayList<Dates> dates = refresh();

                for(int x = 0; x< dates.size(); x++)
                {
                    Dates d = dates.get(x);

                    Date myDate = getLongDate(d);
                    if(d.getUserId() == UserId && myDate == getLongDate(d))
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "There are Tasks here!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
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
        Date currDate = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        String td = day.format(currDate);
        int today = Integer.parseInt(td.toString());
        final int UserId = getIntent().getIntExtra("user_id", 0);
        ArrayList<Dates> dates = refresh();
        for(int x = 0; x< dates.size(); x++)
        {
            Dates d = dates.get(x);
            Date myDates = getLongDate(d);
            if(d.getUserId() == UserId)
            {
                CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
                if(today == d.getDate())
                {
                    if(d.isCompleted())
                    {
                       compactCalendar.setCurrentDayBackgroundColor(2);
                    }
                    else
                    {
                        compactCalendar.setCurrentDayBackgroundColor(1);
                    }
                }


                long millis = myDates.getTime();

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
    public ArrayList<Dates> refresh()
    {
        ArrayList<Dates> dates;
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.getDates();
        dates = manager.getDate_list();

        return dates;
    }

    public Date getLongDate(Dates dates)
    {
        String myDate = Integer.toString(dates.getDate());
        char[] carr = myDate.toCharArray();

        char y = carr[0];
        char e = carr[1];
        char a = carr[2];
        char r = carr[3];
        char mon = carr[4];
        char th = carr[5];
        char d = carr[6];
        char ay = carr[7];

        carr[0] = mon;
        carr[1] = th;
        carr[2] = d;
        carr[3] = ay;
        carr[4] = y;
        carr[5] = e;
        carr[6] = a;
        carr[7] = r;

        myDate = new String(carr);

        myDate = myDate.substring(0, 2) + "/" + myDate.substring(2, myDate.length());
        myDate = myDate.substring(0, 5) + "/" + myDate.substring(5, myDate.length());
        myDate = myDate+" 0:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        java.util.Date date1 = null;
        try {
            date1 = sdf.parse(myDate);
        } catch (ParseException j) {
            j.printStackTrace();
        }
        return date1;
    }


}
