package com.gcorp.knitshceme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawView extends View {

    Paint p;
    Path path;
    int xrows;
    int ycolumns;
    Pattern pattern;

    public DrawView(Context context, Pattern patt){
        super(context);
        p=new Paint();
        p.setStrokeWidth(3);
        p.setStyle(Paint.Style.STROKE);
        path=new Path();
        pattern = patt;
        pattern = patt;
        xrows = pattern.getRows();
        ycolumns = pattern.getColumns();





    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(80, 255, 255, 255);

        Pallet.cell[][] patt = pattern.getPattern();
        //очистка path
        path.reset();

        for (int j = 0; j < xrows * 50; j = j + 50) {
            path.moveTo(0, j);
            path.lineTo(ycolumns * 50, j);
            path.close();
        }

        for (int i = 0; i < ycolumns * 50; i = i + 50) {
            path.moveTo(i, 0);
            path.lineTo(i, xrows * 50);
            path.close();
        }

        //рисуем схему на канве
        //иходник находится в  pattern
        for (int i = 0; i < xrows; i++) {
            for (int j = 0; j < ycolumns; j++) {
                if (patt[i][j]!= Pallet.cell.empty) {
                    printKnit(i * 50, j * 50, patt[i][j], canvas);
                }

            }
        }

        //рисование path
        p.setColor(Color.BLACK);
        canvas.drawPath(path, p);
    }
    //Рисуем петлю в попределенном месте
    public void printKnit(float x, float y, Pallet.cell c, Canvas canvas)
    {
        //Получаем от getRDrawablePNG что за рисунок рисуем его
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), Pattern.getRDrawablePNG(c));
        canvas.drawBitmap(mBitmap,x, y, null);

    }
}