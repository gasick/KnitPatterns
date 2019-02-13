package com.gcorp.knitshceme;



import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class Pattern {

    // Параметры системы
    public enum cell {
        P2TLS, P2TRS, K2TLS, K2TRS, P3TLS, K3TLS, PURL, KNIT, YARNOVER, ILS, IRL, KITB, PITB, EMPTY;
        public static cell getCellValue(String str) {
            return cell.valueOf(str.trim());
        }
        public static String getCellString(cell c) {
            String s;
            switch (c) {
                case P2TLS:s = "P2TLS"; break;
                case P2TRS: s = "P2TRS"; break;
                case K2TLS:s = "K2TLS"; break;
                case K2TRS:s = "K2TRS"; break;
                case P3TLS: s = "P3TLS"; break;
                case K3TLS: s = "K3TLS"; break;
                case PURL: s = "PURL"; break;
                case KNIT: s = "KNIT"; break;
                case YARNOVER: s = "YARNOVER"; break;
                case ILS: s = "ILS"; break;
                case IRL:s = "IRL"; break;
                case KITB: s = "KITB"; break;
                case PITB: s = "PITB"; break;
                default: s = "empty"; break;
            }
            return s;
        }
    }
    public enum menu {undo, redo};

    //Параметры поля редактирования
    float currentX;
    float currentY;
    int displayHeight;
    int displayWidth;
    private int picWidth;
    private int picHeight;
    float magnifier = 1f;
    int widthSizeOfaPic;
    int heightSizeOfaPic;



    //Параметры рисунка
    cell[][] pattern; // схема рисунка
    float picStartx = 0;
    float picStarty = 0;
    private int rows;
    private int columns;
    private cell choosenBrash = cell.KNIT;

    //история действий над рисунком
    Stack<cell[][]> history = new Stack<>();
    Stack<cell[][]> tempHistory = new Stack<>();




    //конструктор класса задает пустой рисунок размером x, y
    Pattern(int x, int y, int picWidth_, int picHeight_) {
        rows = x;
        columns = y;
        pattern = new cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pattern[i][j] = cell.EMPTY;
            }
        }
        Log.i("Pattern value",  Arrays.deepToString(getPattern()));
        Log.i("Cell ILS value", cell.ILS.toString());
        Log.i("Cell value", cell.values()[0].toString());

        picWidth = picWidth_;
        picHeight = picHeight_;
        widthSizeOfaPic = (int)(picWidth * magnifier);
        heightSizeOfaPic = (int)(picHeight * magnifier);
    }


    Pattern(int picWidth_, int picHeight_, String fileName) {
        ArrayList<String> inputFile = FileWork.openFile(fileName);
        Log.i("File path", fileName);
        rows= inputFile.size();
        Log.i("FIRLt line", inputFile.get(0));
        String[] testlength =  inputFile.get(0).split(",");
        Log.i("testlength", testlength.toString());
        columns = testlength.length;
        pattern = new cell[rows][columns];
        Log.i("Pattern value", Arrays.deepToString(getPattern()));
        for (int i = 0; i < rows; i++) {
            String[] line =  inputFile.get(i).split(",");
            Log.i("Array string", Arrays.toString(line));
            Log.i("Cell value", line[0]);
            for (int j = 0; j < columns; j++) {
                Log.i("Cell value",line[j]);
                pattern[i][j] = cell.getCellValue(line[j]);
                Log.i("pattern value",cell.getCellValue(line[j]).toString());
                Log.i("pattern value",pattern[i][j].toString());
            }
        }
        Log.i("Pattern value", Arrays.deepToString(getPattern()));
        picWidth = picWidth_;
        picHeight = picHeight_;
        widthSizeOfaPic = (int)(picWidth * magnifier);
        heightSizeOfaPic = (int)(picHeight * magnifier);
    }

    //Увеличение/уменьшение размера поля
    public void setMagnifier(float newMagnifierValue) {
        Log.d("Magnifire is", String.valueOf(newMagnifierValue));
        if (newMagnifierValue > 0) {
            magnifier = newMagnifierValue;
            widthSizeOfaPic = (int) (picWidth * magnifier);
            heightSizeOfaPic = (int) (picHeight * magnifier);
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
            case P2TLS: return R.drawable.p2tls;
            case P2TRS: return R.drawable.p2trs;
            case K2TLS: return R.drawable.k2tls;
            case K2TRS: return R.drawable.k2trs;
            case P3TLS: return R.drawable.p3tls;
            case K3TLS: return R.drawable.k3tls;
            case PURL: return R.drawable.purl;
            case KNIT: return R.drawable.knit;
            case YARNOVER: return R.drawable.yarnover;
            case ILS: return R.drawable.ils;
            case IRL: return R.drawable.irs;
            case KITB: return R.drawable.kitb;
            case PITB: return R.drawable.pitb;
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
            cell[][] h = (cell[][]) history.elementAt(y);
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
            pattern =(cell[][]) history.peek();
        }
    }
    public void historyRedo() {
        int i = tempHistory.size();
        if (i >= 1) {
            Log.i("historyUndo", "tempHistory.size()");
            pattern =(cell[][]) tempHistory.get(i-1);
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

        Log.i("History full value", String.valueOf(history));

        String temp2 = "";
        for (int i = 0; i < 5; i++) {
            int j = 0;
            cell[][] h = (cell[][]) history.peek();
            Log.i("History h.peek","({" + i + "," + j + "," + h[i][j] + "})");

        }
        for (cell[][] ccc: history) {
            String temp = "";
            for (int i = 0; i < 5; i++) {
                int j = 0;
                temp += "({" + i + "," + j + "," + ccc[i][j] + "})";

            }
            Log.i("History h.elementAt",temp);
        }
        for (int xx = 0; xx < history.size(); xx++) {

        }
        pattern[x][y] = c;

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
