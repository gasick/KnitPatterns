package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Calendar;


public class EditField extends AppCompatActivity {

    Pattern pattern;
    float firstx, firsty, secondx, secondy;
    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;


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

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                firstx = event.getX();
                firsty = event.getY();
                return true;
            }
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                secondx = event.getX();
                secondy = event.getY();
                if (clickDuration < MAX_CLICK_DURATION) {
                    TouchActions.ActionOnTouch(firstx - pattern.startx, firsty - pattern.starty, pattern, EditField.this);
                    setContentView(new DrawView(this, pattern));
                    return true;
                }
                else
                {
                    pattern.startx = (int) (secondx-firstx);
                    pattern.starty = (int) (secondy-firsty);
                    setContentView(new DrawView(this, pattern));
                    return true;

                }

            }


        }
        return false;

    }
}


