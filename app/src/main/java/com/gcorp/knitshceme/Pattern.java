package com.gcorp.knitshceme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;


public class Pattern {

    // Параметры системы
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
    cell[][] pattern; // схема рисунка
    //история действий над рисунком
    ArrayList<cell[][]> history = new ArrayList<>();
    ArrayList<Pattern.cell[][]> tempHistory = new ArrayList<>();


    //Статичная информация
    public enum cell {
        p2tls, p2trs, k2tls, k2trs, p3tls, k3tls, purl, knit, yarnover, ils, irs, kitb, pitb, empty
    }

    private cell choosenBrash = cell.knit;

    public enum menu {revert, undo};

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
            case revert:
                return R.drawable.revert;
            case undo:
                return R.drawable.undo;
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

    public void addEditHistory(Pattern.cell[][] patt) {
        history.add(patt);
        tempHistory.clear();
        pattern = history.get(history.size() - 1);

    }
    public void undoEditHistory() {
        if (history.size() > 1) {
            int i = (history.size() - 1);
            tempHistory.add(history.get(i));
            history.remove(i);
            pattern = history.get(i - 1);
        }
        pattern = history.get(history.size());
    }
    public void redoEditHistory() {
        if (tempHistory.size() > 0) {
            int i = (tempHistory.size() - 1);
            history.add(tempHistory.get(i));
            tempHistory.remove(i);
        }
        pattern =  history.get(history.size() - 1);
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
        addEditHistory(pattern);
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
