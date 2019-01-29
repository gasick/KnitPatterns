package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;


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
    Stack<cell[][]> history = new Stack<>();
    Stack<cell[][]> tempHistory = new Stack<>();




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
            case undo: return R.drawable.undo;
            case redo: return R.drawable.redo;
            default: return -1;
        }
    }

    //на запрос пользователя о том, что это за Cell
    //возвращаем значение рисунка png из drawable
    public static int getRDrawablePNG(cell c) {
        switch (c) {
            case p2tls: return R.drawable.p2tls;
            case p2trs: return R.drawable.p2trs;
            case k2tls: return R.drawable.k2tls;
            case k2trs: return R.drawable.k2trs;
            case p3tls: return R.drawable.p3tls;
            case k3tls: return R.drawable.k3tls;
            case purl: return R.drawable.purl;
            case knit: return R.drawable.knit;
            case yarnover: return R.drawable.yarnover;
            case ils: return R.drawable.ils;
            case irs: return R.drawable.irs;
            case kitb: return R.drawable.kitb;
            case pitb: return R.drawable.pitb;
            default: return -1;
        }
    }

    public void addHistory() {
        history.push(pattern);
        Log.i("add History", "history.add");
        int iii = history.size();
        Log.i("History size", String.valueOf(iii));
        String temp = "";
        String temp1 = "";
        String temp2 = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp += "({" + i + "," + j + "," + pattern[i][j] + "})";
            }
        }
        Log.i("History pattern", temp);
        for (int y  = 0; y<history.size(); y++) {

            cell[][] h = history.elementAt(y);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    temp1 += "({" + i + "," + j + "," + h[i][j] + "})";

                }
            }

            Log.i("History h", temp1);
            temp1 = "";
        }

        /*
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp2 += "({" + i + "," + j + "," + pattern[i][j] + "})";
            }
        }
        Log.i("History temp", temp2);
*/
        tempHistory.clear();
        Log.i("add History", "tempHistory.clear");
    }
    public void historyUndo() {
        if (!history.empty()){
            tempHistory.push(history.pop());
            pattern = history.peek();
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
        history.push(getPattern());
        String temp2 = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp2 += "({" + i + "," + j + "," + history.lastElement()[i][j] + "})";
            }
        }
        Log.i("History history.atI", temp2);
        pattern[x][y] = c;
        String temp = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp += "({" + i + "," + j + "," + pattern[i][j] + "})";
            }
        }
        Log.i("History changePatCell", temp);
        String temp1 = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp1 += "({" + i + "," + j + "," + history.lastElement()[i][j] + "})";
            }
        }
        Log.i("History history.atI", temp1);
        String temp3 = String.valueOf(history.size());
        Log.i("History history.atI", temp3);
        if (history.size()>4) {
            String temp4 = "";
            history.pop();
            cell[][] h = history.firstElement();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    temp4 += "({" + i + "," + j + "," + h[i][j] + "})";
                }
            }
            Log.i("History history.atI", temp4);
        }


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
