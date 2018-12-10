package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.User;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Intent intent = getIntent();
        final int userId = intent.getIntExtra("user", 0);

        TextView usersName = findViewById(R.id.usersName);
        User user = getUser(userId);
        usersName.setText(user.getName());
        TextView highscore = findViewById(R.id.highscore);
        TextView streak = findViewById(R.id.currentStreak);

        int h = user.getHighcscore();
        int s = user.getStreak();
        highscore.setText(h+"");
        streak.setText(s+"");

        ImageButton addtask = findViewById(R.id.addTaskBtn);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton home = findViewById(R.id.homeBtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public ArrayList<User> refresh()
    {
        ArrayList<User> users;
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.getUsers();
        users = manager.getUser_list();

        return users;
    }

    public User getUser(int userId)
    {
        ArrayList<User> users = refresh();
        for(int x = 0; x<users.size(); x++)
        {
            if(users.get(x).getId() == userId)
            {
                return users.get(x);
            }

        }
        return null;
    }


}
