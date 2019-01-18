package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class EditField extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);


        Intent intent = getIntent();

        int rows = Integer.parseInt(intent.getStringExtra("rows"));
        int columns =  Integer.parseInt(intent.getStringExtra("columns"));

        setContentView(new DrawView(this, rows, columns));
    }
}
