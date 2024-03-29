package com.gcorp.knitshceme;

import android.widget.Toast;

//Класс обрабатывающий нажатия
public class TouchActions {
    public static void ActionOnTouch(Pattern patt, EditField_Activity ef) {
        // Высчитываем то куда было нажание указываем это для switch
        //нужно придумать проверку switch и ее дальнейшее использование.


        if ((int) patt.currentY > (patt.displayHeight - patt.heightSizeOfaPic)) {
            int i = (int) patt.currentX / patt.heightSizeOfaPic;
            if (i <= Pattern.cell.values().length)
                patt.setChoosenBrash(Pattern.cell.values()[i]);
            Toast.makeText(
                    ef, patt.currentX + " и " + patt.currentY + " Выбрана кисть", Toast.LENGTH_LONG
            ).show();
        }
        //Обрабатываем нажатия на значки меню.
        if ((int) patt.currentY > (patt.displayHeight - (patt.heightSizeOfaPic * 2))) {
            int i = (int) patt.currentX / patt.heightSizeOfaPic;
            if (i <= Pattern.menu.values().length) {
                if (i == 0) {
                    patt.historyUndo();
                    Toast.makeText(ef, i + "  Выбрано меню", Toast.LENGTH_LONG).show();
                }
                if (i == 1) {
                    patt.historyRedo();
                    Toast.makeText(ef, i + "  Выбрано меню", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            int xNew = ((int) (patt.currentX - patt.picStartx) / patt.heightSizeOfaPic);
            //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
            int yNew = ((int) (patt.currentY - patt.picStarty) / patt.widthSizeOfaPic)-4;
            if (xNew >= patt.getRows() || yNew >= patt.getColumns()
                    || xNew < 0 || yNew < 0) {
                Toast.makeText
                        (
                                ef, xNew + " и " + yNew + " вне поля редактирования " +
                                        patt.currentX + " и " + patt.currentY,
                                Toast.LENGTH_LONG
                        ).show();
            } else {
                Toast.makeText(ef, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
                patt.changePatternCell(xNew, yNew, patt.getChoosenBrush());
            }
        }
    }
}
