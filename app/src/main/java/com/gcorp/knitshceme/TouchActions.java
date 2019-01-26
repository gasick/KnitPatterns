package com.gcorp.knitshceme;

import android.widget.Toast;

//Класс обрабатывающий нажатия
public class TouchActions {
    public  static void ActionOnTouch(float x, float y, Pattern patt, EditField ef) {
        // Высчитываем то куда было нажание указываем это для switch
        //нужно придумать проверку switch и ее дальнейшее использование.


        if ((int)y > (patt.displayHeight - patt.heightOfaPic))
        {
            int i = (int) x/patt.heightOfaPic;
            if (i <= Pattern.cell.values().length)
                patt.setChoosenBrash(Pattern.cell.values()[i]);
            Toast.makeText(ef, x + " и " + y  + " " + i+ " и "+ patt.displayHeight + "  Выбрана кисть", Toast.LENGTH_LONG).show();
        }
        //Обрабатываем нажатия на значки меню.
        if ((int)y > (patt.displayHeight - (patt.heightOfaPic*2))){
            int i = (int) x/patt.heightOfaPic;
            if (i <= Pattern.menu.values().length)
            {
                if (i == 0)
                {
                    patt.updatePattern(patt.historyActions.cancelEditHistory());
                    Toast.makeText(ef, i+"  Выбрано меню", Toast.LENGTH_LONG).show();
                }
                if (i==1)
                {
                    patt.updatePattern(patt.historyActions.undoEditHistory());
                    Toast.makeText(ef, i+"  Выбрано меню", Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            int xNew = ((int) x / patt.heightOfaPic);
            //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
            int yNew = ((int) y / patt.widthOfaPic) - 4;
            if (xNew >= patt.getRows() || yNew >= patt.getColumns() || xNew <= patt.startx || yNew <= patt.starty) {
                Toast.makeText(ef, xNew + " и " + yNew  + " и "+ patt.displayHeight  + " вне поля редактирования " + x + " и " + y, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ef, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
                patt.changePatternCell(xNew, yNew, patt.getChoosenBrush());
            }
        }
    }
}
