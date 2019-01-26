package com.gcorp.knitshceme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;


public class EditField extends AppCompatActivity {

    Pattern pattern;
    paintField pField = paintField.newInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, pField)
                    .commitNow();
        pField.setIntent(getIntent());

        }
    }

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                pattern.startx = (int) x - (pattern.getRows()/2)*pattern.widthOfaPic;
                pattern.starty = (int) y-(pattern.getColumns()/2)*pattern.heightOfaPic;
                pField.DrawOnFragmet(pattern);
                return false;
            case MotionEvent.ACTION_DOWN:
                TouchActions.ActionOnTouch(x - pattern.startx, y - pattern.starty, pattern, this);
                pField.DrawOnFragmet(pattern);
                return true;
        }
        return false;
    }

}

