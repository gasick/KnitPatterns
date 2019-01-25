package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;


public class EditField extends AppCompatActivity {

    Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);

        Intent intent = getIntent();

        int rows = Integer.parseInt(intent.getStringExtra("rows"));
        int columns = Integer.parseInt(intent.getStringExtra("columns"));
        pattern = new Pattern(rows, columns);
        setContentView(new DrawView(this, pattern));
    }

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                pattern.startx = (int) x - (pattern.getRows()/2)*pattern.widthOfaPic;
                pattern.starty = (int) y-(pattern.getColumns()/2)*pattern.heightOfaPic;
                setContentView(new DrawView(this, pattern));
                return true;
            case MotionEvent.ACTION_DOWN:
                TouchActions.ActionOnTouch(x-pattern.startx, y-pattern.starty, pattern, EditField.this);
                setContentView(new DrawView(this, pattern));
                return true;

        }
        return false;
    }

}

