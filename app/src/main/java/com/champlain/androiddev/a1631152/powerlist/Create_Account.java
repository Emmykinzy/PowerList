package com.champlain.androiddev.a1631152.powerlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Create_Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

        //OnClick will clear text in Email TextView
        EditText name = findViewById(R.id.name);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.name);
                name.getText().clear();
            }
        });

        //OnClick will clear text in Email TextView
        EditText user = findViewById(R.id.username);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = findViewById(R.id.username);
                user.getText().clear();
            }
        });
    }
}
