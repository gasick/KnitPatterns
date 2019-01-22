package com.gcorp.knitshceme;

import java.util.ArrayList;

public class History {

    ArrayList<Pattern.cell[][]> history = new ArrayList<>();
    ArrayList<Pattern.cell[][]> tempHistory = new ArrayList<>();

    public void History(Pattern.cell[][] patt){
        history.add(patt);
    }
    public Pattern.cell[][] addEditHistory(Pattern.cell[][] patt) {
        history.add(patt);
        tempHistory = null;
        return history.get(history.size() - 1);
    }
    public Pattern.cell[][] backwardEditHistory(){
        int i  = history.size();
        tempHistory.add(history.get(i-1));
        history.remove(i);
        return history.get(history.size() - 1);

    }
    public Pattern.cell[][] forwardEditHistory() {
        if (tempHistory.size() > 0) {
            int i = tempHistory.size() - 1;
            history.add(tempHistory.get(i));
            tempHistory.remove(i);
        }
        return history.get(history.size() - 1);
    }

}
