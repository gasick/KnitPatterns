package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;


public class Pattern {

    // Параметры системы
    public enum cell {
        p2tls, p2trs, k2tls, k2trs, p3tls, k3tls, purl, knit, yarnover, ils, irs, kitb, pitb, empty
    }

    public enum menu {undo, redo};

    float currentX;
    float currentY;
    int displayHeight;
    int displayWidth;
    int widthOfaPic;
    int heightOfaPic;

    //Параметры рисунка
    float picStartx = 0;
    float picStarty = 0;
    private int rows;
    private int columns;
    private cell choosenBrash = cell.knit;
    cell[][] pattern; // схема рисунка
    //история действий над рисунком
    ArrayList<cell[][]> history = new ArrayList<>();
    ArrayList<Pattern.cell[][]> tempHistory = new ArrayList<>();



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

    //получение R.drawable объекта пункта меню по названию пункта меню
    public static int getRDrawablePNG(menu m) {
        switch (m) {
            case undo:
                return R.drawable.undo;
            case redo:
                return R.drawable.redo;
            default:
                return -1;
        }
    }

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

    public void addHistory(Pattern.cell[][] patt) {
       history.add(patt);
       Log.i("addHisotry", "history.add");
       tempHistory.clear();
       Log.i("addHisotry", "tempHistory.clear");
    }
    public void historyUndo() {
        if( history.size()>= 1) {
            Log.i("historyUndo", "history.size()");
            tempHistory.add(history.get(history.size()-1));
            Log.i("historyUndo", "tempHistory.add(history.get(history.size");
            history.remove(history.size()-1);
            Log.i("historyUndo", "history.remove(history.size()");
            pattern = history.get(history.size()-1);
            Log.i("historyUndo", "pattern = history.get(history.size()");
        }
    }
    public void historyRedo() {
        int i = tempHistory.size();
        if (i >= 1) {
            Log.i("historyUndo", "tempHistory.size()");
            pattern = tempHistory.get(i-1);
            Log.i("historyUndo", "pattern = tempHistory.get");
            history.add(tempHistory.get(i-1));
            Log.i("historyUndo", "history.add(tempHistory.get(");
            tempHistory.remove(i-1);
            Log.i("historyUndo", "tempHistory.remove(");
        }
    }

    public void updatePattern(cell[][] patt) {

    }

    //получае схему рисунка
    public cell[][] getPattern() {
        return pattern;
    }

    //Изменяем схему рисунка
    public void changePatternCell(int x, int y, cell c) {
        pattern[x][y] = c;
        addHistory(pattern);
    }

    //Получаем количество рядов схемы
    public int getRows() {
        return rows;
    }

    //Получаем количество петель схемы
    public int getColumns() {
        return columns;
    }

    //Узнать выбранную кисть редактирования
    public cell getChoosenBrush() {
        return choosenBrash;
    }

    //Установить выбранную кисть редактирования
    public void setChoosenBrash(cell c) {
        choosenBrash = c;
    }
}
