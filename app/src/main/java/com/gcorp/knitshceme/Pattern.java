package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Pattern {

    //Параметры рисунка
    int height;
    int width;
    int widthOfaPic;
    int heightOfaPic;
    private int rows;
    private int columns;
    private cell[][] pattern;

    //Статичная информация
    public enum cell  {p2tls, p2trs,k2tls,k2trs,p3tls,k3tls,purl,knit,yarnover,ils,irs,kitb,pitb,empty}
    static cell[] cells=  new cell[] {cell.p2tls, cell.p2trs,cell.k2tls,cell.k2trs,cell.p3tls,cell.k3tls,cell.purl,cell.knit,cell.yarnover,cell.ils,cell.irs,cell.kitb,cell.pitb};
    private cell choosenBrash = cell.knit;

    //на запрос пользователя о том, что это за Cell
    //возвращаем значение рисунка png из drawable
    public static int getRDrawablePNG(cell c) {
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
        pattern = new cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pattern[i][j] = cell.empty;
            }
        }
    }

    //получае схему рисунка
    public cell[][] getPattern() {
        return pattern;
    }

    //Изменяем схему рисунка
    public void changePattern(int x, int y, cell c) {
        pattern[x][y] = c;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public cell getChoosenBrush()
    {
        return choosenBrash;
    }
    public void setChoosenBrash(cell c){
        choosenBrash = c;
    }
}
