package com.champlain.androiddev.a1631152.powerlist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.champlain.androiddev.a1631152.powerlist.Models.Dates;
import com.champlain.androiddev.a1631152.powerlist.Models.User;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;


import java.util.ArrayList;

public class DBSQLiteManager extends SQLiteOpenHelper{
    private static ArrayList<User> user_list = new ArrayList<>();
    private static ArrayList<Task> task_list = new ArrayList<>();
    private static ArrayList<Dates> dates_list = new ArrayList<>();

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
                user_list.add(u);
            }while (cursor.moveToNext());
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
                task_list.add(t);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void updateTasks(int userId, int dateId, boolean c1, boolean c2, boolean c3, boolean c4, boolean c5, String d1, String d2, String d3, String d4, String d5)
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

        String whereClause = Task.COLUMN_DID+" = '"+dateId+"' AND "+Task.COLUMN_UID+" ='"+userId+"'";

        db.update(Task.TABLE_NAME, contentValues, whereClause+"",null);

    }

    public void getTasksWithId(int userId)
    {
        String selectQuery = "SELECT * FROM " + Task.TABLE_NAME + " WHERE " +
                Task.COLUMN_UID + " = '"+userId+"'";
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
                task_list.add(t);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public void getDates()
    {
        String selectQuery = "SELECT * FROM " + Task.TABLE_NAME + " ORDER BY " +
                Task.COLUMN_UID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Dates d = new Dates();

                d.setUserId(cursor.getInt(cursor.getColumnIndex(Task.COLUMN_UID)));
                d.setDate(cursor.getInt(cursor.getColumnIndex(Task.COLUMN_DID)));

                boolean b1 = (intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK1)))));
                boolean b2 = (intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK2)))));
                boolean b3 = (intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK3)))));
                boolean b4 = (intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK4)))));
                boolean b5 = (intToBool((cursor.getInt(cursor.getColumnIndex(Task.COLUMN_TASK5)))));

                if(b1 && b2 && b3 && b4 && b5)
                {
                    d.setCompleted(true);
                }
                else
                {
                    d.setCompleted(false);
                }

                dates_list.add(d);
            }while (cursor.moveToNext());
        }
        db.close();
    }

    public ArrayList<User> getUser_list()
    {
        // return contact list
        return user_list;
    }

    public ArrayList<Dates> getDate_list()
    {
        // return contact list
        return dates_list;
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
