package com.champlain.androiddev.a1631152.powerlist.Models;

public class Task {

    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_TASKID = "task_id";
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
    public static final String COLUMN_COMPLETE = "completed";


    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DID + " INT, "
            + COLUMN_UID + " INT, "
            + COLUMN_TASK1 + " BOOLEAN, "
            + COLUMN_DESCRIPTION1 + " TEXT, "
            + COLUMN_TASK2 + " BOOLEAN, "
            + COLUMN_DESCRIPTION2 + " TEXT, "
            + COLUMN_TASK3 + " BOOLEAN, "
            + COLUMN_DESCRIPTION3 + " TEXT, "
            + COLUMN_TASK4 + " BOOLEAN, "
            + COLUMN_DESCRIPTION4 + " TEXT, "
            + COLUMN_TASK5 + " BOOLEAN, "
            + COLUMN_DESCRIPTION5 + " TEXT,"
            + COLUMN_COMPLETE + " BOOLEAN)";

    private int user_id;
    private int data_id;
    private Boolean task1;
    private String description1;
    private Boolean task2;
    private String description2;
    private Boolean task3;
    private String description3;
    private Boolean task4;
    private String description4;
    private Boolean task5;
    private String description5;
    private Boolean completed;

    public Task() {}

    public Task(int user_id, int data_id, Boolean task1, String description1, Boolean task2, String description2, Boolean task3, String description3, Boolean task4, String description4, Boolean task5, String description5, Boolean completed)
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
        this.completed = completed;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getData_id()
    {
        return data_id;
    }

    public void setData_id(int data_id)
    {
        this.data_id = data_id;
    }

    public Boolean getTask1()
    {
        return task1;
    }

    public void setTask1(Boolean task1)
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

    public Boolean getTask2()
    {
        return task2;
    }

    public void setTask2(Boolean task2)
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

    public Boolean getTask3()
    {
        return task3;
    }

    public void setTask3(Boolean task3)
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

    public Boolean getTask4() {
        return task4;
    }

    public void setTask4(Boolean task4)
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

    public Boolean getTask5()
    {
        return task5;
    }

    public void setTask5(Boolean task5)
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

    public Boolean getCompleted()
    {
        return completed;
    }

    public void setCompleted(Boolean completed)
    {
        this.completed = completed;
    }
}


