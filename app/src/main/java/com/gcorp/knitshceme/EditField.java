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
        int columns =  Integer.parseInt(intent.getStringExtra("columns"));
        pattern = new Pattern(rows, columns);
        setContentView(new DrawView(this, pattern));
}

    //Метод слушающий нажатие.
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            int xNew = ((int)x/50);
            //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
            int yNew = ((int)y/50)-4;
            if ( xNew > pattern.getRows() || yNew > pattern.getColumns() )
            {Toast.makeText(EditField.this, xNew+ " и " + yNew +" вне поля редактирования " + x+ " и " + y , Toast.LENGTH_LONG).show();}
            else {
                Toast.makeText(EditField.this, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
                pattern.changePattern(xNew, yNew, Pallet.cells.knit);
                setContentView(new DrawView(this, pattern));
                return true;
            }
        }
        return false;

    }
}
