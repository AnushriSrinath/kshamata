package com.example.anu.kshamata;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class display_details extends AppCompatActivity {
View view;

    TextView name,dob, age, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = (TextView)findViewById(R.id.name);
        dob = (TextView)findViewById(R.id.dob);
        age = (TextView)findViewById(R.id.age);
        phone = (TextView)findViewById(R.id.phone);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        displayContact(view);
    }

    public void displayContact(View view) {
        dbAdapter db = new dbAdapter(this);
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst()) {
            do {
                display(c);
            } while (c.moveToNext());
        }
        db.close();
    }

    public void display(Cursor c)
    {

        name.setText("NAME: " + c.getString(1));
        dob.setText("DOB: " + c.getString(2));
        age.setText("AGE: " + c.getString(3));
        phone.setText("PHONE: " + c.getString(4));

    }
}
