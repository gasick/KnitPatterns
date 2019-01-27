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

        //Рисуем пустое поле размером rows на columns
        int rows = Integer.parseInt(intent.getStringExtra("rows"));
        int columns = Integer.parseInt(intent.getStringExtra("columns"));
        //создаем объект этого поля в памяти.
        pattern = new Pattern(rows, columns);
        //Рисуем канву со всемы вытекающими
        setContentView(new DrawView(this, pattern));
    }

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            //Проверяем касание
            //Нажатие на экран
            case MotionEvent.ACTION_DOWN: {
                //записывает когда нажание началось
                startClickTime = Calendar.getInstance().getTimeInMillis();
                //Получаем координаты положения нажатия
                firstx = event.getX();
                firsty = event.getY();
                return true;
            }//Отпускаем экран
            case MotionEvent.ACTION_UP: {
                //высчитываем время между нажатием на экран и тем когда экран был отпущен
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                //получаем координаты отпускания
                secondx = event.getX();
                secondy = event.getY();
                //Если нажание длилось больше чем 200 мс то значит это редактирование.
                //Тогда мы просто ставим в клетку тип петли
                if (clickDuration < MAX_CLICK_DURATION) {
                    pattern.currentX = (int)(firstx);
                    pattern.currentY = (int) (firsty);
                    TouchActions.ActionOnTouch( pattern, EditField.this);
                    setContentView(new DrawView(this, pattern));
                    return true;
                }
                //Если нажание было долгое, то скорей всего это перемещеение
                //Тогда двигается рисунок относительно экрана.
                else
                {
                    pattern.picStartx = pattern.picStartx  + (int) (secondx-firstx);
                    pattern.picStarty = pattern.picStarty + (int) (secondy-firsty);
                    setContentView(new DrawView(this, pattern));
                    return true;

                }

            }


        }
        return false;

    }
}


