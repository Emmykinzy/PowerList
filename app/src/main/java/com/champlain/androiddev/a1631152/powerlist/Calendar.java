package com.champlain.androiddev.a1631152.powerlist;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;
import com.champlain.androiddev.a1631152.powerlist.Models.User;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar extends AppCompatActivity {

    /*
                        ****
    The calendar in use is a third party project and can
    be found at https://github.com/SundeepK/CompactCalendarView
                        ****

     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final int UserId = getIntent().getIntExtra("user_id", 0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");

        final CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        compactCalendar.setFirstDayOfWeek(1);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        getTasks();
        instantiate();


        //Click to Open Profile Page
        ImageButton profileBtn = findViewById(R.id.profile);
        profileBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Profile.class);
                i.putExtra("user", UserId);
                startActivity(i);

            }
        });

        //Click to add tasks or edit todays tasks
        ImageButton addTask = findViewById(R.id.addTaskBtn);
        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArrayList<Task> tasks = refreshT(UserId);

                if(tasks != null)
                {

                    boolean itsToday = false;
                    for (int x = 0; x < tasks.size(); x++)
                    {
                        Task t = tasks.get(x);
                        int dateId = t.getData_id();

                        int today = todaysDateId();

                        if (dateId == today)
                        {
                            itsToday = true;
                            openTask(t, 1);
                            break;
                        }

                    }
                    if (!itsToday) {
                        Intent i = new Intent(getBaseContext(), addTask.class);
                        i.putExtra("id", UserId);
                        startActivityForResult(i, 0);
                    }

                }
                else
                {
                    Intent i = new Intent(getBaseContext(), addTask.class);
                    i.putExtra("id", UserId);
                    startActivityForResult(i, 0);
                }

            }
        });


        //Handle calender events
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener()
        {
            @Override
            public void onDayClick(java.util.Date dateClicked)
            {
                ArrayList<Task> tasks = refreshT(UserId);
                Date d = new Date();
                SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
                String date = day.format(d);
                int today = Integer.parseInt(date.toString());

                if(tasks != null)
                {
                    for (int x = 0; x < tasks.size(); x++)
                    {
                        Task t = tasks.get(x);

                        Date myDate = getLongDate(t.getData_id());
                        if (myDate.equals(dateClicked))
                        {

                            if (t.getCompleted()) {
                                compactCalendar.setCurrentSelectedDayBackgroundColor(Color.rgb(76, 226, 6));
                            } else {
                                compactCalendar.setCurrentSelectedDayBackgroundColor(Color.RED);
                            }

                            if (dateClicked.equals(getLongDate(today))) {
                                openTask(t, 1);
                            } else {
                                openTask(t, 0);

                            }

                            break;
                        }
                        else
                        {
                            compactCalendar.setCurrentSelectedDayBackgroundColor(Color.rgb(86, 168, 255));

                        }

                    }
                }
            }

            @Override
            public void onMonthScroll(java.util.Date firstDayOfNewMonth) {

                CompactCalendarView c = findViewById(R.id.compactcalendar_view);
                c.setCurrentSelectedDayBackgroundColor(Color.rgb(86,168,255));

                Date d = new Date(firstDayOfNewMonth.getTime());
                SimpleDateFormat month = new SimpleDateFormat("MM");
                SimpleDateFormat year = new SimpleDateFormat("yyyy");


                String md = month.format(d);
                int monthDate = Integer.parseInt(md);
                String yd = year.format(d);
                int yearDate = Integer.parseInt(yd);

                String monthText = getMonth(monthDate-1);

                ActionBar actionBar = getSupportActionBar();
                actionBar.setTitle(monthText+" "+yearDate);
            }
        });


    }



    //Instantiate checks for events and updates the calendarView accordingly
    public void instantiate()
    {
        Date currDate = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        String md = month.format(currDate);
        int monthDate = Integer.parseInt(md);
        String yd = year.format(currDate);
        int yearDate = Integer.parseInt(yd);

        String monthText = getMonth(monthDate-1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(monthText+" "+yearDate);

        String td = day.format(currDate);
        int today = Integer.parseInt(td);
        final int UserId = getIntent().getIntExtra("user_id", 0);
        ArrayList<Task> tasks = refreshT(UserId);
        if(tasks.size() != 0) {
            boolean noToday = true;
            for (int x = 0; x < tasks.size(); x++) {
                Task t = tasks.get(x);
                Date myDates = getLongDate(t.getData_id());
                final CompactCalendarView compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
                if (today == t.getData_id()) {
                    changeColor(t.getCompleted(), compactCalendar);
                    noToday = false;
                }

                long millis = myDates.getTime();

                if(!dateHasEvent(millis))
                {
                    createEvent(t.getData_id(), t.getCompleted(), compactCalendar);
                }

                if(noToday)
                {
                    compactCalendar.setCurrentDayBackgroundColor(Color.rgb(86,168,255));
                    compactCalendar.setCurrentSelectedDayBackgroundColor(Color.rgb(86,168,255));
                }

            }

        }

    }


    //refreshT returns an ArrayList of a User's tasks
    public ArrayList<Task> refreshT(int userId)
    {
        ArrayList<Task> tasks;
        DBSQLiteManager manager = new DBSQLiteManager(this);

        tasks = manager.getTasksWithId(userId);

        return tasks;
    }

    //refresh returns an ArrayList of all the Users
    public ArrayList<User> refresh()
    {
        final ArrayList<User> u;
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);
        u = manager.updateUserList();

        return u;
    }
    //Gets all the tasks in the database
    public void getTasks()
    {
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);
        manager.getTasks();

    }

    //Returns a date in MM/dd/yyyy HH:mm:ss format from a tasks dateId
    public Date getLongDate(int dates)
    {
        String myDate = Integer.toString(dates);
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
        Date date1 = null;
        try {
            date1 = sdf.parse(myDate);
        } catch (ParseException j) {
            j.printStackTrace();
        }
        return date1;
    }

    //Checks if a date in milliseconds has an event and returns a boolean
    public boolean dateHasEvent(long millis)
    {
        CompactCalendarView compactCalendar = findViewById(R.id.compactcalendar_view);
        List<Event> e = compactCalendar.getEvents(millis);
        if(e.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Will open an activity to either view or edit a task based on the int passed
    public void openTask(Task t, int x)
    {
        Intent et;
        if(x == 0)
        {
             et = new Intent(getBaseContext(), task_view.class);
        }
        else
        {
             et = new Intent(getBaseContext(), edit_tasks.class);
        }

        et.putExtra("uId", t.getUser_id());

        et.putExtra("dId", t.getData_id());

        et.putExtra("ch1", t.getTask1()+"");
        et.putExtra("ch2", t.getTask2()+"");
        et.putExtra("ch3", t.getTask3()+"");
        et.putExtra("ch4", t.getTask4()+"");
        et.putExtra("ch5", t.getTask5()+"");

        et.putExtra("d1", t.getDescription1()+"");
        et.putExtra("d2", t.getDescription2()+"");
        et.putExtra("d3", t.getDescription3()+"");
        et.putExtra("d4", t.getDescription4()+"");
        et.putExtra("d5", t.getDescription5()+"");

        startActivityForResult(et, 0);
    }

    //Will return today's date formatted as yyyyMMdd and as an int
    public int todaysDateId()
    {
        Date date = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        String todayString = day.format(date);
        int today = Integer.valueOf(todayString);

        return today;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        CompactCalendarView compactCalendar = findViewById(R.id.compactcalendar_view);
        super.onActivityResult(requestCode, resultCode, data);
        //If task was added
        if(resultCode == 1)
        {
            int d = data.getIntExtra("date", 0);
            String comp = data.getStringExtra("comp");
            boolean b = sTb(comp);

            createEvent(d, b, compactCalendar);

        }
        //If task was edited
        else if(resultCode == 2)
        {

            String comp = data.getStringExtra("comp");
            boolean b = sTb(comp);
            changeColor(b, compactCalendar);
            int id = Integer.parseInt(data.getStringExtra("id"));

            User u = getUser(id);
            //Check if completed
            if(b)
            {

                int lastD = u.getLast_completed();
                /*
                  If last day completed was today. In case people change from
                  completed back to uncompleted and back again so as to not add to the streak counter.
                */
                if(u.getLast_completed() == lastD)
                {

                }
                else
                {
                    //Check if last completed day was yesterday
                    if (lastD == (todaysDateId() - 1)) {
                        u.setLast_completed(todaysDateId());
                        int streak = u.getStreak();
                        int h = u.getHighcscore();

                        u.setStreak(streak + 1);
                        if (u.getStreak() > h) {
                            u.setHighcscore(u.getStreak());
                        }
                    }
                    //Reset Streak Counter
                    else {
                        u.setLast_completed(todaysDateId());
                        u.setStreak(1);
                        if (u.getHighcscore() == 0) {
                            u.setHighcscore(1);
                        }
                    }

                    updateUser(u);
                }

            }


        }
        else if(resultCode == 3)
        {

        }

    }
    //Add task to database and create calendar event as well
    private Task addTask(Task t, CompactCalendarView c)
    {
        createEvent(t.getData_id(), t.getCompleted(), c);
        changeColor(t.getCompleted(), c);

        DBSQLiteManager dbslm = new DBSQLiteManager(this);
        Task createdTask = dbslm.addTask(t);

        return createdTask;
    }

    //Change the colour of selected day and current day based on passed boolean
    public void changeColor(boolean completed, CompactCalendarView c)
    {
        if(completed)
        {
            c.setCurrentSelectedDayBackgroundColor(Color.rgb(76, 226, 6));
            c.setCurrentDayBackgroundColor(Color.rgb(76, 226, 6));
        }
        else
        {
            c.setCurrentSelectedDayBackgroundColor(Color.RED);
            c.setCurrentDayBackgroundColor(Color.RED);
        }
    }

    //Add event to calendar
    public void createEvent(int date, boolean completed, CompactCalendarView c)
    {
        Date myDates = getLongDate(date);
        long millis = myDates.getTime();
        if(!dateHasEvent(millis))
        {
            if (completed) {
                Event ev1 = new Event((Color.rgb(76, 226, 6)), millis);
                c.addEvent(ev1);
            } else {
                Event ev1 = new Event(Color.RED, millis);
                c.addEvent(ev1);
            }
        }
        changeColor(completed, c);
    }

    //Convert string to boolean
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

    //Return User from userId
   public User getUser(int userId)
   {
       ArrayList<User> users = refresh();
       for(int x=0;x<users.size(); x++)
       {
           User u = users.get(x);
           if(u.getId() == userId)
           {
               return u;
           }
       }
       return null;
   }

   //Update User's Streak, last completed task date and high score
    public void updateUser(User u)
    {
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.updateUser(u.getId(), u.getLast_completed(), u.getStreak(), u.getHighcscore());
        refresh();

    }

    //Get String month based on passed int
    public String getMonth(int month)
    {
        String[] months = new String[12];
        months[0] = "January";
        months[1] = "February";
        months[2] = "March";
        months[3] = "April";
        months[4] = "May";
        months[5] = "June";
        months[6] = "July";
        months[7] = "August";
        months[8] = "September";
        months[9] = "October";
        months[10] = "November";
        months[11] = "December";


        return months[month];

    }


}
