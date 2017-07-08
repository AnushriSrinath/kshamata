package com.example.anu.kshamata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class update_info extends AppCompatActivity {
    dbAdapter db = new dbAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
    }
}

