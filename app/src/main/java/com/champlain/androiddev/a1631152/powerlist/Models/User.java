package com.champlain.androiddev.a1631152.powerlist.Models;

public class User {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_PASSWORD + " TEXT, "
            + COLUMN_NAME + " TEXT)";

    private Integer id;
    private String email;
    private String password;
    private String name;

    public User() {}

    public User(Integer id, String email, String password, String name){
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public  String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
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
