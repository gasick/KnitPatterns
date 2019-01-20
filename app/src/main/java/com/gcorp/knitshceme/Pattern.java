package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Pattern {

    private int rows;
    private int columns;
    private Pallet.cell[][] pattern = new Pallet.cell[rows][columns];
    int height;
    int width;
    int widthOfaPic;
    int heightOfaPic;

    private Pallet.cell choosenBrash = Pallet.cell.knit;


    //на запрос пользователя о том, что это за Cell
    //возвращаем значение рисунка png из drawable
    public static int getRDrawablePNG(Pallet.cell c) {
        switch (c) {
            case p2tls:
                return R.drawable.p2tls;
            case p2trs:
                return R.drawable.p2trs;
            case k2tls:
                return R.drawable.k2tls;
            case k2trs:
                return R.drawable.k2trs;
            case p3tls:
                return R.drawable.p3tls;
            case k3tls:
                return R.drawable.k3tls;
            case purl:
                return R.drawable.purl;
            case knit:
                return R.drawable.knit;
            case yarnover:
                return R.drawable.yarnover;
            case ils:
                return R.drawable.ils;
            case irs:
                return R.drawable.irs;
            case kitb:
                return R.drawable.kitb;
            case pitb:
                return R.drawable.pitb;
            default:
                return -1;

        }
    }

    //конструктор класса задает пустой рисунок размером x, y
    Pattern(int x, int y) {
        rows = x;
        columns = y;
        pattern = new Pallet.cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pattern[i][j] = Pallet.cell.empty;
            }
        }
    }

    //получае схему рисунка
    public Pallet.cell[][] getPattern() {
        return pattern;
    }

    //Изменяем схему рисунка
    public void changePattern(int x, int y, Pallet.cell c) {
        pattern[x][y] = c;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Pallet.cell getChoosenBrush()
    {
        return choosenBrash;
    }
    public void setChoosenBrash(Pallet.cell c){
        choosenBrash = c;
    }
}
