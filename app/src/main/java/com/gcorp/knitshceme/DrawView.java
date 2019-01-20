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

    Bitmap testBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.knit);

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
        xrows = pattern.getRows();
        ycolumns = pattern.getColumns();
        pattern.widthOfaPic = testBitmap.getWidth();
        pattern.heightOfaPic = testBitmap.getHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawARGB(80, 255, 255, 255);
        pattern.displayWidth = getWidth();
        pattern.displayHeight = getHeight();
        Pattern.cell[][] patt = pattern.getPattern();
        //очистка path
        path.reset();

        for (int j = 0; j < xrows * pattern.widthOfaPic; j = j + pattern.widthOfaPic) {
            path.moveTo(0, j);
            path.lineTo(ycolumns * pattern.heightOfaPic, j);
            path.close();
        }

        for (int i = 0; i < ycolumns * pattern.heightOfaPic; i = i + pattern.heightOfaPic) {
            path.moveTo(i, 0);
            path.lineTo(i, xrows * pattern.widthOfaPic);
            path.close();
        }

        //рисуем схему на канве
        //иходник находится в  pattern
        for (int i = 0; i < xrows; i++) {
            for (int j = 0; j < ycolumns; j++) {
                if (patt[i][j]!= Pattern.cell.empty) {
                    printKnit(i * pattern.widthOfaPic, j * pattern.heightOfaPic, patt[i][j], canvas);
                }

            }
        }

        drawPallet(canvas);
        drawMenu(canvas);

        //рисование path
        p.setColor(Color.BLACK);
        canvas.drawPath(path, p);
    }

    //Рисуем меню
    private void drawMenu(Canvas canvas) {
        int yPalletMenuHeight =  getHeight() - (pattern.heightOfaPic*2);
        int menuTypesLenght = Pattern.menu.values().length;
        for (int i = 0; i<menuTypesLenght; i++)
        {
            printMenu(i*pattern.heightOfaPic, yPalletMenuHeight, Pattern.menu.values()[i], canvas);
        }
    }

    //
    private void printMenu(float x, float y, Pattern.menu m, Canvas canvas) {
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), Pattern.getRDrawablePNG(m));
        canvas.drawBitmap(mBitmap,x, y, null);
    }

    //Рисуем петлю в попределенном месте
    public void printKnit(float x, float y, Pattern.cell c, Canvas canvas)
    {
        //Получаем от getRDrawablePNG что за рисунок рисуем его
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), Pattern.getRDrawablePNG(c));
        canvas.drawBitmap(mBitmap,x, y, null);

    }

    public void drawPallet(Canvas canvas)
    {
        int yPalletKnitHeight =  getHeight() - pattern.heightOfaPic;
        int KnitTypesLenght = Pattern.cell.values().length;
        for (int i = 0; i < KnitTypesLenght; i++) {
            if (Pattern.cell.values()[i] != Pattern.cell.empty) {
                printKnit(i * pattern.widthOfaPic, yPalletKnitHeight, Pattern.cell.values()[i], canvas);
            }
        }
    }

}