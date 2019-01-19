package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Pattern {

    private int rows;
    private int columns;
    private Pallet.cell[][] pattern = new Pallet.cell[rows][columns];


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

        pattern[1][1] = Pallet.cell.purl;
        pattern[1][2] = Pallet.cell.purl;
        pattern[1][3] = Pallet.cell.purl;
        pattern[1][4] = Pallet.cell.purl;
        pattern[1][5] = Pallet.cell.purl;

        pattern[2][1] = Pallet.cell.purl;
        pattern[2][2] = Pallet.cell.yarnover;
        pattern[2][3] = Pallet.cell.purl;
        pattern[2][4] = Pallet.cell.yarnover;
        pattern[2][5] = Pallet.cell.knit;

        pattern[3][1] = Pallet.cell.purl;
        pattern[3][2] = Pallet.cell.purl;
        pattern[3][3] = Pallet.cell.purl;
        pattern[3][4] = Pallet.cell.purl;
        pattern[3][5] = Pallet.cell.purl;

        pattern[4][1] = Pallet.cell.purl;
        pattern[4][2] = Pallet.cell.yarnover;
        pattern[4][3] = Pallet.cell.purl;
        pattern[4][4] = Pallet.cell.yarnover;
        pattern[4][5] = Pallet.cell.knit;

        pattern[5][1] = Pallet.cell.purl;
        pattern[5][2] = Pallet.cell.purl;
        pattern[5][3] = Pallet.cell.purl;
        pattern[5][4] = Pallet.cell.purl;
        pattern[5][5] = Pallet.cell.purl;

        pattern[6][1] = Pallet.cell.purl;
        pattern[6][2] = Pallet.cell.yarnover;
        pattern[6][3] = Pallet.cell.purl;
        pattern[6][4] = Pallet.cell.yarnover;
        pattern[6][5] = Pallet.cell.knit;

    }

    //получае схему рисунка
    public Pallet.cell[][] getPattern() {
        return pattern;
    }

    //Изменяем схему рисунка
    public void changePattern(int x, int y, Pallet.cell c) {
        pattern[x][y] = Pallet.cell.knit;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns() {
        return columns;
    }


}
