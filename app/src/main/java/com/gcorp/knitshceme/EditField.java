package com.gcorp.knitshceme;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    float x,y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);
        Bitmap testBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.knit);
        Intent intent = getIntent();

        //Рисуем пустое поле размером rows на columns
        int rows = Integer.parseInt(intent.getStringExtra("rows"));
        int columns = Integer.parseInt(intent.getStringExtra("columns"));

        //создаем объект этого поля в памяти.
        pattern = new Pattern(rows, columns, testBitmap.getWidth(),testBitmap.getHeight());
        //Рисуем канву со всемы вытекающими
        setContentView(new DrawView(this, pattern));
    }

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            //Проверяем касание
            //Нажатие на экран
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();

            case MotionEvent.ACTION_UP:
                pattern.currentX = (int) event.getX();
                pattern.currentY = (int) event.getY();
                TouchActions.ActionOnTouch(pattern, EditField.this);
                setContentView(new DrawView(this, pattern));
                break;
            case MotionEvent.ACTION_MOVE:
                //Тогда двигается рисунок относительно экрана.
                pattern.picStartx = pattern.picStartx + (int) (event.getX() - x);
                pattern.picStarty = pattern.picStarty + (int) (event.getY() - y);
                setContentView(new DrawView(this, pattern));
                break;
        }



        return true;

    }
}


