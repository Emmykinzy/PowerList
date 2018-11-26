package com.champlain.androiddev.a1631152.powerlist.Models;

public class Date {

    private int date;
    private int userId;
    private boolean completed;
    public Date() {}

    public Date(int date, int userId, boolean completed)
    {
        this.date = date;
        this.userId = userId;
        this.completed = completed;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
