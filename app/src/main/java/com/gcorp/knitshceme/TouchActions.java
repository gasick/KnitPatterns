package com.gcorp.knitshceme;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.gcorp.knitshceme.DrawView;
import com.gcorp.knitshceme.EditField;
import com.gcorp.knitshceme.Pallet;
import com.gcorp.knitshceme.Pattern;


public class TouchActions {
    public  static void ActionOnTouch(float x, float y, Pattern pattern, EditField ef) {
        // Высчитываем то куда было нажание указываем это для switch
        //нужно придумать проверку switch и ее дальнейшее использование.
        if ((int)y > (pattern.height - 50))
        {
            int i = (int) x/50;
            if (i <= Pallet.cell.values().length)
            pattern.setChoosenBrash(Pallet.cell.values()[i]);
            Toast.makeText(ef, x + " и " + y  + " " + i+ " и "+ pattern.height + "  Выбрана кисть", Toast.LENGTH_LONG).show();
        }
        else {
            int xNew = ((int) x / 50);
            //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
            int yNew = ((int) y / 50) - 4;
            if (xNew > pattern.getRows() || yNew > pattern.getColumns()) {
                Toast.makeText(ef, xNew + " и " + yNew  + " и "+ pattern.height  + " вне поля редактирования " + x + " и " + y, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ef, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
                pattern.changePattern(xNew, yNew, pattern.getChoosenBrush());
            }
        }
    }
}
