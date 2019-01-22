package com.gcorp.knitshceme;

import java.util.ArrayList;

public class History {

    ArrayList<Pattern.cell[][]> history = new ArrayList<>();
    ArrayList<Pattern.cell[][]> tempHistory = new ArrayList<>();


    public Pattern.cell[][] addEditHistory(Pattern.cell[][] patt) {
        history.add(patt);
        tempHistory.clear();
        return history.get(history.size() - 1);
    }
    public Pattern.cell[][] cancelEditHistory(){
        int i  = (history.size()-1);
        tempHistory.add(history.get(i));
        history.remove(i);
        return history.get(i- 1);

    }
    public Pattern.cell[][] undoEditHistory() {
        if (tempHistory.size() > 0) {
            int i = (tempHistory.size() - 1);
            history.add(tempHistory.get(i));
            tempHistory.remove(i);
        }
        return history.get(history.size() - 1);
    }

}
