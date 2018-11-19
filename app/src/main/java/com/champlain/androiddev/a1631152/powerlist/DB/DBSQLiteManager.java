package com.champlain.androiddev.a1631152.powerlist.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.champlain.androiddev.a1631152.powerlist.Models.User;
import com.champlain.androiddev.a1631152.powerlist.Models.Tasks;


import java.util.ArrayList;

public class DBSQLiteManager extends SQLiteOpenHelper{
    private static ArrayList<User> user_list = new ArrayList<>();

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
        db.execSQL(Tasks.CREATE_TABLE);
        db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing, like a boss :)
    }
}
