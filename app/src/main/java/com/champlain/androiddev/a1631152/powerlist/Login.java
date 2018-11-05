package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Create_Account {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //commit test..
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Calendar.class);
                startActivityForResult(i, 1);
            }
        });


       TextView new_account = findViewById(R.id.no_account);

       //OnClick will clear text in Email TextView
        EditText user = findViewById(R.id.username);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = findViewById(R.id.username);
                user.getText().clear();
            }
        });

        //OnClick will clear text in Password TextView
        EditText pass = findViewById(R.id.pass_confirm);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pass = findViewById(R.id.pass_confirm);
                pass.getText().clear();
            }
        });

        //OnClick will open Login Page
        new_account.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Create_Account.class);

                startActivityForResult(i, 1);
            }

        });
    }
}
