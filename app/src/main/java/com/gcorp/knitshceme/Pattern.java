package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public  class Pattern {

    int rows;
    int columns;
    private Pallet.cell[][] pattern = new Pallet.cell[rows][columns];

    Pallet.cell c = Pallet.cell.pitb;


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

        public void Pattern( int x, int y)
    {
        rows = x;
        columns = y;
        for (int i = 0; i < x; i++){
            for (int j = 0; i < y; i++){
                pattern[i][j]= Pallet.cell.empty;
            }
        }
    }
    public Pallet.cell[][] getPattern(){
        return pattern;
    }

    public void patternPrinter(Pattern patt){
            //for (int i)
    }
}
