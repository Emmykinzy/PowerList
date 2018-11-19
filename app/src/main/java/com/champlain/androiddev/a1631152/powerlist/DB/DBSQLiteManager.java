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
                user_list.add(u);
            }while (cursor.moveToNext());
        }
        db.close();
    }


    public ArrayList<User> getContact_list()
    {
        // return contact list
        return user_list;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       String a = Task.CREATE_TABLE;

        db.execSQL(Task.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing, like a boss :)
    }
}
