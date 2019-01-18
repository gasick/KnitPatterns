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
    int x;
    int y;

    public DrawView(Context context, int rows, int columns){
        super(context);
        p=new Paint();
        p.setStrokeWidth(3);
        p.setStyle(Paint.Style.STROKE);
        path=new Path();
        x = rows;
        y = columns;



    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawARGB(80,255,255,255);

        int height=canvas.getHeight();
        int width=canvas.getWidth();

        //очистка path
        path.reset();

        for(int j=0;j<x*50;j=j+50)
        {
            path.moveTo(0,j);
            path.lineTo(y*50,j);
            path.close();
        }

        for(int i=0;i<y*50;i=i+50)
        {
            path.moveTo(i,0);
            path.lineTo(i,x*50);
            path.close();
        }

        //рисование path
        p.setColor(Color.BLACK);
        canvas.drawPath(path,p);
    }
    //Рисуем петлю в попределенном месте
    public void printKnit(int x, int y, Pallet.cell c, Canvas canvas)
    {
        //Получаем от getRDrawablePNG что за рисунок рисуем его
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), Pattern.getRDrawablePNG(c));
        canvas.drawBitmap(mBitmap,x, y, null);

    }
}