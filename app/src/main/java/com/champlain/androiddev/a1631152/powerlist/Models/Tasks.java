package com.champlain.androiddev.a1631152.powerlist.Models;

public class Tasks {

    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_UID = "user_id";
    public static final String COLUMN_DID = "date_id";
    public static final String COLUMN_TASK1 = "task1";
    public static final String COLUMN_DESCRIPTION1 = "description1";
    public static final String COLUMN_TASK2 = "task2";
    public static final String COLUMN_DESCRIPTION2 = "description2";
    public static final String COLUMN_TASK3 = "task3";
    public static final String COLUMN_DESCRIPTION3 = "description3";
    public static final String COLUMN_TASK4 = "task4";
    public static final String COLUMN_DESCRIPTION4 = "description4";
    public static final String COLUMN_TASK5 = "task5";
    public static final String COLUMN_DESCRIPTION5 = "description5";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_DID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_UID + " INT, "
            + COLUMN_TASK1 + " INT, "
            + COLUMN_DESCRIPTION1 + " TEXT, "
            + COLUMN_TASK2 + " TEXT, "
            + COLUMN_DESCRIPTION2 + " TEXT, "
            + COLUMN_TASK3 + " TEXT, "
            + COLUMN_DESCRIPTION3 + " TEXT, "
            + COLUMN_TASK4 + " TEXT, "
            + COLUMN_DESCRIPTION4 + " TEXT, "
            + COLUMN_TASK5 + " TEXT, "
            + COLUMN_DESCRIPTION5 + " TEXT)";

    private Integer user_id;
    private Integer data_id;
    private Integer task1;
    private String description1;
    private Integer task2;
    private String description2;
    private Integer task3;
    private String description3;
    private Integer task4;
    private String description4;
    private Integer task5;
    private String description5;

    public Tasks() {}

    public Tasks(Integer user_id, Integer data_id, Integer task1, String description1, Integer task2, String description2, Integer task3, String description3, Integer task4, String description4, Integer task5, String description5)
    {
        this.user_id = user_id;
        this.data_id = data_id;
        this.task1 = task1;
        this.description1 = description1;
        this.task2 = task2;
        this.description2 = description2;
        this.task3 = task3;
        this.description3 = description3;
        this.task4 = task4;
        this.description4 = description4;
        this.task5 = task5;
        this.description5 = description5;
    }

    public Integer getUser_id()
    {
        return user_id;
    }

    public void setUser_id(Integer user_id)
    {
        this.user_id = user_id;
    }

    public Integer getData_id()
    {
        return data_id;
    }

    public void setData_id(Integer data_id)
    {
        this.data_id = data_id;
    }

    public Integer getTask1()
    {
        return task1;
    }

    public void setTask1(Integer task1)
    {
        this.task1 = task1;
    }

    public String getDescription1()
    {
        return description1;
    }

    public void setDescription1(String description1)
    {
        this.description1 = description1;
    }

    public Integer getTask2()
    {
        return task2;
    }

    public void setTask2(Integer task2)
    {
        this.task2 = task2;
    }

    public String getDescription2()
    {
        return description2;
    }

    public void setDescription2(String description2)
    {
        this.description2 = description2;
    }

    public Integer getTask3()
    {
        return task3;
    }

    public void setTask3(Integer task3)
    {
        this.task3 = task3;
    }

    public String getDescription3()
    {
        return description3;
    }

    public void setDescription3(String description3)
    {
        this.description3 = description3;
    }

    public Integer getTask4() {
        return task4;
    }

    public void setTask4(Integer task4)
    {
        this.task4 = task4;
    }

    public String getDescription4()
    {
        return description4;
    }

    public void setDescription4(String description4)
    {
        this.description4 = description4;
    }

    public Integer getTask5()
    {
        return task5;
    }

    public void setTask5(Integer task5)
    {
        this.task5 = task5;
    }

    public String getDescription5()
    {
        return description5;
    }

    public void setDescription5(String description5)
    {
        this.description5 = description5;
    }
}


