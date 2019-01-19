package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;


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

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            Toast.makeText(EditField.this, " x = " + x + " y = " + y, Toast.LENGTH_LONG).show();
            int xNew = (int)x/100;
            int yNew = (int)y/100;
            return true;
        }
        return false;

    }
}
