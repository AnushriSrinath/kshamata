package com.example.anu.kshamata;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class display_details extends AppCompatActivity {
View view;
    dbAdapter DB;

    TextView name,dob, age, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        DB = new dbAdapter(this,null,null,1);


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

        ListView listview = (ListView)findViewById(R.id.listView);
        DB = new dbAdapter(this,null,null,1);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = DB.getListContents();

        if(data.getCount() == 0)
        {
            Toast.makeText(this,"DB doesn't exist",Toast.LENGTH_LONG).show();
        }

        else
        {
            while(data.moveToNext())
            {
                theList.add(data.getString(1));
                ListAdapter listadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                listview.setAdapter(listadapter);



                        //String s = (String) adapterView.getItemAtPosition(i);
//                        Toast.makeText(view.getContext(),"Fetching data..",Toast.LENGTH_SHORT).show();


                        ListView listviw = (ListView)findViewById(R.id.listView);

                        ArrayList<String> thList = new ArrayList<>();
                        Cursor dat = DB.getListContents();



                            while(dat.moveToNext())
                            {
                                thList.add(dat.getString(1));
                                ListAdapter listadaptr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                                listviw.setAdapter(listadaptr);




                            }


                        //}





            }
        }



    }
}
