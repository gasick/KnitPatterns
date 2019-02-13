package com.gcorp.knitshceme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import java.text.DecimalFormat;
import java.util.Calendar;


public class EditField_Activity extends AppCompatActivity {

    Pattern pattern;
    private long startClickTime;
    float x,y;
    private VelocityTracker mVelocityTracker = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);
        Bitmap testBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.knit);
        Intent intent = getIntent();

        String action = intent.getStringExtra("action");
        switch (action){
            case "create":
                //Рисуем пустое поле размером rows на columns
                int rows = Integer.parseInt(intent.getStringExtra("rows"));
                int columns = Integer.parseInt(intent.getStringExtra("columns"));

                //создаем объект этого поля в памяти.
                pattern = new Pattern(rows, columns, testBitmap.getWidth(),testBitmap.getHeight());
                break;
            case "open":
                String filePath = intent.getStringExtra("uri");
                pattern = new Pattern(testBitmap.getWidth(),testBitmap.getHeight(),filePath );
                break;
        }


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
                startClickTime = Calendar.getInstance().getTimeInMillis();
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;

            case MotionEvent.ACTION_UP:
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if (clickDuration < 200) {
                    pattern.currentX = (int) event.getX();
                    pattern.currentY = (int) event.getY();
                    TouchActions.ActionOnTouch(pattern, EditField_Activity.this);
                    setContentView(new DrawView(this, pattern));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1){
                    mVelocityTracker.addMovement(event);
                    DecimalFormat df = new DecimalFormat("0.00");
                    mVelocityTracker.computeCurrentVelocity(5);
                    pattern.setMagnifier((float)((Math.sqrt(Math.pow(mVelocityTracker.getXVelocity(),2)+Math.pow(mVelocityTracker.getYVelocity(),2)))));
                    setContentView(new DrawView(this, pattern));
                }else {
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(50);
                    //Тогда двигается рисунок относительно экрана.
                    pattern.picStartx = pattern.picStartx + (int) mVelocityTracker.getXVelocity();
                    pattern.picStarty = pattern.picStarty + (int) mVelocityTracker.getYVelocity();
                    setContentView(new DrawView(this, pattern));
                    break;
                }
        }



        return true;

    }
}


