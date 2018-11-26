package com.champlain.androiddev.a1631152.powerlist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.champlain.androiddev.a1631152.powerlist.Models.User;

import com.champlain.androiddev.a1631152.powerlist.DB.DBSQLiteManager;
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

                EditText name = findViewById(R.id.name);
                EditText email = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);
                EditText pass_confirm = findViewById(R.id.pass_confirm);

                String name1=name.getText().toString();
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                String pass_confirm1 = pass_confirm.getText().toString();

                if(password1.equals(pass_confirm1) && !name1.isEmpty() && !email1.isEmpty())
                {
                    User u = new User(1, email.getText().toString(), password.getText().toString(), name.getText().toString());
                    addUser(u);
                    finish();

                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Passwords don't match!, Enter Name/Email", Toast.LENGTH_SHORT);
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
}
