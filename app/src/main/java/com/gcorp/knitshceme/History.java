package com.gcorp.knitshceme;

public class History {
    Pattern[] pattern = new Pattern[10];
    pattern
    pattern[] history = new pattern[10];

    public void forwardHistory(Pattern patt) {
        for (int i=0;i<9;i++){
            history[i] = history[i+1];
        }
        history[9] = patt.pattern;
    }
    public void backwardHistory(Pattern patt){

    }
}
