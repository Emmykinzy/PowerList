package com.champlain.androiddev.a1631152.powerlist.Models;

public class User {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LAST_COMPLETED = "last_completed";
    public static final String COLUMN_STREAK = "streak";
    public static final String COLUMN_HIGNSCORE = "highscore";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "
            + COLUMN_NAME + " TEXT,"
            + COLUMN_LAST_COMPLETED + " INT, "
            + COLUMN_STREAK + " INT, "
            + COLUMN_HIGNSCORE + " INT)";

    private int id;
    private String email;
    private String password;
    private String name;
    private int last_completed;
    private int streak;
    private int highcscore;

    public User() {}

    public User(int id, String email, String password, String name){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.last_completed = 0;
        this.streak = 0;
        this. highcscore = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLast_completed() {
        return last_completed;
    }

    public void setLast_completed(int last_completed) {
        this.last_completed = last_completed;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getHighcscore() {
        return highcscore;
    }

    public void setHighcscore(int highcscore) {
        this.highcscore = highcscore;
    }

    public boolean search(String email)
    {
        if(this.getEmail().equals(email))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
