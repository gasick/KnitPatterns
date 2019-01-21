package com.gcorp.knitshceme;

import android.widget.Toast;

//Класс обрабатывающий нажатия
public class TouchActions {
    public  static void ActionOnTouch(float x, float y, Pattern pattern, EditField ef) {
        // Высчитываем то куда было нажание указываем это для switch
        //нужно придумать проверку switch и ее дальнейшее использование.
        if ((int)y > (pattern.displayHeight - pattern.heightOfaPic))
        {
            int i = (int) x/pattern.heightOfaPic;
            if (i <= Pattern.cell.values().length)
            pattern.setChoosenBrash(Pattern.cell.values()[i]);
            Toast.makeText(ef, x + " и " + y  + " " + i+ " и "+ pattern.displayHeight + "  Выбрана кисть", Toast.LENGTH_LONG).show();
        }
        if ((int)y > (pattern.displayHeight - (pattern.heightOfaPic*2))){
            int i = (int) x/pattern.heightOfaPic;
            if (i <= Pattern.menu.values().length)
            Toast.makeText(ef, x + " и " + y  + " " + i+"  Выбрано меню", Toast.LENGTH_LONG).show();
        }
        else {
            int xNew = ((int) x / pattern.heightOfaPic);
            //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
            int yNew = ((int) y / pattern.widthOfaPic) - 4;
            if (xNew > pattern.getRows() || yNew > pattern.getColumns()) {
                Toast.makeText(ef, xNew + " и " + yNew  + " и "+ pattern.displayHeight  + " вне поля редактирования " + x + " и " + y, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ef, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
                pattern.changePattern(xNew, yNew, pattern.getChoosenBrush());
            }
        }
    }
}
