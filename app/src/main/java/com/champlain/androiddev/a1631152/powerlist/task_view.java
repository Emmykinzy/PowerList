package com.champlain.androiddev.a1631152.powerlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class task_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        final Intent intent = getIntent();
        final int userId = intent.getIntExtra("id", 0);

        TextView title = findViewById(R.id.date);

        TextView t1 = findViewById(R.id.d1);
        TextView t2 = findViewById(R.id.d2);
        TextView t3 = findViewById(R.id.d3);
        TextView t4 = findViewById(R.id.d4);
        TextView t5 = findViewById(R.id.d5);

        CheckBox c1 = findViewById(R.id.ch1);
        CheckBox c2 = findViewById(R.id.ch2);
        CheckBox c3 = findViewById(R.id.ch3);
        CheckBox c4 = findViewById(R.id.ch4);
        CheckBox c5 = findViewById(R.id.ch5);

        int dateId = intent.getIntExtra("dId", 0);
        String myDate = Integer.toString(dateId);


        myDate = myDate.substring(0, 4) + "/" + myDate.substring(4, myDate.length());
        myDate = myDate.substring(0, 7) + "/" + myDate.substring(7, myDate.length());

        title.setText(myDate+" Tasks");

        c1.setChecked(sTb(intent.getStringExtra("ch1")));
        c2.setChecked(sTb(intent.getStringExtra("ch2")));
        c3.setChecked(sTb(intent.getStringExtra("ch3")));
        c4.setChecked(sTb(intent.getStringExtra("ch4")));
        c5.setChecked(sTb(intent.getStringExtra("ch5")));

        t1.setText(intent.getStringExtra("d1"));
        t2.setText(intent.getStringExtra("d2"));
        t3.setText(intent.getStringExtra("d3"));
        t4.setText(intent.getStringExtra("d4"));
        t5.setText(intent.getStringExtra("d5"));
    }


    private boolean sTb(String sb)
    {
        String t = "true";
        if(sb.equals(t))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


