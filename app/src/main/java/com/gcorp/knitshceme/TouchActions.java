package com.gcorp.knitshceme;

import android.widget.Toast;

import com.gcorp.knitshceme.DrawView;
import com.gcorp.knitshceme.EditField;
import com.gcorp.knitshceme.Pallet;
import com.gcorp.knitshceme.Pattern;

public class TouchActions {

    public  static void ActionOnTouch(float x, float y, Pattern pattern) {
        int xNew = ((int) x / 50);
        //-4 для сдвига относительно оси y в противном случае рисунок уходит от точки касания.
        int yNew = ((int) y / 50) - 4;
        if (xNew > pattern.getRows() || yNew > pattern.getColumns()) {
            //Toast.makeText(EditField.this, xNew + " и " + yNew + " вне поля редактирования " + x + " и " + y, Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(EditField.this, " x = " + xNew + " y = " + yNew, Toast.LENGTH_LONG).show();
            pattern.changePattern(xNew, yNew, Pallet.cell.knit);
        }
    }
}
