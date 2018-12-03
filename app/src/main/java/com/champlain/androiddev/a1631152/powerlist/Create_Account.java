package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.Models.User;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;

import java.util.ArrayList;

public class Create_Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!= null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Button bAdd = findViewById(R.id.create_account);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<User> users = refresh();
                EditText name = findViewById(R.id.name);
                EditText email = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);
                EditText pass_confirm = findViewById(R.id.pass_confirm);

                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String pass_confirm1 = pass_confirm.getText().toString();

                if(password1.equals(pass_confirm1) && !name1.isEmpty() && !email1.isEmpty())
                {
                    boolean realUser = false;
                    for(int x = 0; x<users.size(); x++)
                    {
                        User u = users.get(x);
                        if(u.getEmail().equals(email1))
                        {
                            realUser = true;
                        }
                    }
                    if(!realUser)
                    {
                        User u = new User(1, email.getText().toString(), password.getText().toString(), name.getText().toString());
                        addUser(u);
                        finish();
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });


    }

    private User addUser(User c)
    {
        //Contact createdContact = DBManager.addContact(c);

        DBSQLiteManager dbslm = new DBSQLiteManager(this);
        User createdUser = dbslm.addUser(c);
        Toast toast = Toast.makeText(getApplicationContext(), "New User Created", Toast.LENGTH_SHORT);
        toast.show();

        return createdUser;
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
}
