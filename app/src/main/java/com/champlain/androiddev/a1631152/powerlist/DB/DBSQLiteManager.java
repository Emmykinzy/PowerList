package com.champlain.androiddev.a1631152.powerlist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.champlain.androiddev.a1631152.powerlist.Models.User;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;


import java.util.ArrayList;

public class DBSQLiteManager extends SQLiteOpenHelper{
    private static ArrayList<User> user_list = new ArrayList<>();
    private static ArrayList<Task> task_list = new ArrayList<>();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "user_db";

    public DBSQLiteManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public User addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.COLUMN_EMAIL, u.getEmail());
        values.put(User.COLUMN_PASSWORD, u.getPassword());
        values.put(User.COLUMN_NAME, u.getName());
        values.put(User.COLUMN_LAST_COMPLETED, u.getLast_completed());
        values.put(User.COLUMN_STREAK, u.getStreak());
        values.put(User.COLUMN_HIGNSCORE, u.getHighcscore());

        db.insert(User.TABLE_NAME, null, values);
        db.close();
        user_list.add(u);
        return u;
    }

    public Task addTask(Task t)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.COLUMN_UID, t.getUser_id());
        values.put(Task.COLUMN_DID, t.getData_id());
        values.put(Task.COLUMN_TASK1, t.getTask1());
        values.put(Task.COLUMN_DESCRIPTION1, t.getDescription1());
        values.put(Task.COLUMN_TASK2, t.getTask2());
        values.put(Task.COLUMN_DESCRIPTION2, t.getDescription2());
        values.put(Task.COLUMN_TASK3, t.getTask3());
        values.put(Task.COLUMN_DESCRIPTION3, t.getDescription3());
        values.put(Task.COLUMN_TASK4, t.getTask4());
        values.put(Task.COLUMN_DESCRIPTION4, t.getDescription4());
        values.put(Task.COLUMN_TASK5, t.getTask5());
        values.put(Task.COLUMN_DESCRIPTION5, t.getDescription5());
        values.put(Task.COLUMN_COMPLETE, t.getCompleted());

        db.insert(Task.TABLE_NAME, null, values);
        db.close();
        task_list.add(t);
        return t;
    }

    public void getUsers()
    {
        String selectQuery = "SELECT * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_EMAIL + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                User u = new User();
                u.setId(cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)));
                u.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                u.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                u.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
                u.setLast_completed(cursor.getInt(cursor.getColumnIndex(User.COLUMN_LAST_COMPLETED)));
                u.setStreak(cursor.getInt(cursor.getColumnIndex(User.COLUMN_STREAK)));
                u.setHighcscore(cursor.getInt(cursor.getColumnIndex(User.COLUMN_HIGNSCORE)));
                user_list.add(u);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void updateUser(int userId, int last_completed, int streak, int highscore)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(User.COLUMN_LAST_COMPLETED, last_completed);
        contentValues.put(User.COLUMN_STREAK, streak);
        contentValues.put(User.COLUMN_HIGNSCORE, highscore);

        String whereClause = User.COLUMN_ID+" = '"+userId+"'";

        db.update(User.TABLE_NAME, contentValues, whereClause+"",null);
        for(int x = 0; x < user_list.size(); x++)
        {
            User u = user_list.get(x);
            if(u.getId() == userId)
            {
                u.setLast_completed(last_completed);
                u.setStreak(streak);
                u.setHighcscore(highscore);
            }
        }
        db.close();

    }

    public boolean intToBool(int a)
    {
       if(a == 0)
       {
           return false;
       }
       else
       {
           return true;
       }

    }
    public void getTasks()
    {
        String selectQuery = "SELECT * FROM " + Task.TABLE_NAME + " ORDER BY " +
                Task.COLUMN_UID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Task t = new Task();
                t.setUser_id(cursor.getInt(cursor.getColumnIndex(Task.COLUMN_UID)));
                t.setData_id(cursor.getInt(cursor.getColumnIndex(Task.COLUMN_DID)));
                t.setTask1(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK1)))));
                t.setDescription1(cursor.getString(cursor.getColumnIndex(Task.COLUMN_DESCRIPTION1)));
                t.setTask2(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK2)))));
                t.setDescription2(cursor.getString(cursor.getColumnIndex(Task.COLUMN_DESCRIPTION2)));
                t.setTask3(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK3)))));
                t.setDescription3(cursor.getString(cursor.getColumnIndex(Task.COLUMN_DESCRIPTION3)));
                t.setTask4(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK4)))));
                t.setDescription4(cursor.getString(cursor.getColumnIndex(Task.COLUMN_DESCRIPTION4)));
                t.setTask5(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK5)))));
                t.setDescription5(cursor.getString(cursor.getColumnIndex(Task.COLUMN_DESCRIPTION5)));
                t.setCompleted(intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_COMPLETE)))));
                task_list.add(t);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void updateTasks(int userId, int dateId, boolean c1, boolean c2, boolean c3, boolean c4, boolean c5, String d1, String d2, String d3, String d4, String d5, Boolean complete)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Task.COLUMN_TASK1, c1);
        contentValues.put(Task.COLUMN_TASK2, c2);
        contentValues.put(Task.COLUMN_TASK3, c3);
        contentValues.put(Task.COLUMN_TASK4, c4);
        contentValues.put(Task.COLUMN_TASK5, c5);

        contentValues.put(Task.COLUMN_DESCRIPTION1, d1);
        contentValues.put(Task.COLUMN_DESCRIPTION2, d2);
        contentValues.put(Task.COLUMN_DESCRIPTION3, d3);
        contentValues.put(Task.COLUMN_DESCRIPTION4, d4);
        contentValues.put(Task.COLUMN_DESCRIPTION5, d5);

        contentValues.put(Task.COLUMN_COMPLETE, complete);

        String whereClause = Task.COLUMN_DID+" = '"+dateId+"' AND "+Task.COLUMN_UID+" ='"+userId+"'";

        db.update(Task.TABLE_NAME, contentValues, whereClause+"",null);
        for(int x = 0; x < task_list.size(); x++)
        {
            Task t = task_list.get(x);
            if(t.getUser_id() == userId && t.getData_id() == dateId)
            {
                t.setTask1(c1);
                t.setDescription1(d1);
                t.setTask2(c2);
                t.setDescription2(d2);
                t.setTask3(c3);
                t.setDescription3(d3);
                t.setTask4(c4);
                t.setDescription4(d4);
                t.setTask5(c5);
                t.setDescription5(d5);
                t.setCompleted(complete);
            }
        }
        db.close();

    }

    public ArrayList<Task> getTasksWithId(int userId)
    {
        ArrayList<Task> task = new ArrayList<>();
        for(int x = 0; x < task_list.size(); x++)
        {

            Task t = task_list.get(x);
            if(t.getUser_id() == userId)
            {
               task.add(t);
            }
        }

        return task;
    }

    public ArrayList<User> updateUserList()
    {
        ArrayList<User> user = new ArrayList<>();
        for(int x = 0; x < user_list.size(); x++)
        {
            User u = user_list.get(x);
            user.add(u);
        }

        return user;
    }

    public ArrayList<User> getUser_list()
    {
        // return contact list
        return user_list;
    }

    public ArrayList<Task> getTask_list()
    {
        // return contact list
        return task_list;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Task.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing, like a boss :)
    }
}
