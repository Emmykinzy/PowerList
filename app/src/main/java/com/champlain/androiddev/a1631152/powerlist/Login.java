package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
import com.champlain.androiddev.a1631152.powerlist.Models.Task;
import com.champlain.androiddev.a1631152.powerlist.Models.User;

import java.util.ArrayList;

public class Login extends Create_Account {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button face = findViewById(R.id.log_facebook);

        getUser();


        TextView new_account = findViewById(R.id.no_account);


        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                EditText user = (EditText) findViewById(R.id.username);
                EditText pass = (EditText) findViewById(R.id.password);

                String userText = user.getText().toString();
                String passText = pass.getText().toString();

                final ArrayList<User> us = refresh();
                boolean realUser = false;
               for(int x = 0; x<us.size(); x++)
               {
                   User u = us.get(x);
                   if(u.getPassword().equals(passText) && u.getEmail().equals(userText))
                   {
                       Intent i = new Intent(getBaseContext(), Calendar.class);
                       i.putExtra("user_id", u.getId());
                       startActivity(i);
                       realUser = true;
                   }
               }

               if(!realUser)
               {
                   Toast toast = Toast.makeText(getApplicationContext(), "Email or Password Incorrect!", Toast.LENGTH_SHORT);
                   toast.show();
                   user.setText("");
                   pass.setText("");
               }


            }
        });

        //OnClick will open Login Page
        new_account.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Create_Account.class);

                startActivity(i);
            }

        });
    }

    public ArrayList<User> refresh()
    {
        final ArrayList<User> u;
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);
        u = manager.updateUserList();

        return u;
    }

    public void getUser()
    {
        DBSQLiteManager manager;
        manager = new DBSQLiteManager(this);

        manager.getUsers();
    }

}
