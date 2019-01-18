package com.gcorp.knitshceme;

public class Pattern {
    enum cell  {p2tls, p2trs,k2tls,k2trs,p3tls,k3tls,purl,knit,yarnover,ils,irs,kitb,pitb,empty}
    int rows;
    int columns;
    private cell[][] pattern = new cell[rows][columns];


    public void Pattern( int x, int y)
    {
        rows = x;
        columns = y;
        for (int i = 0; i < x; i++){
            for (int j = 0; i < y; i++){
                pattern[i][j]= cell.empty;
            }
        }
    }
    public cell[][] getPattern(){
        return pattern;
    }

    public void patternPrinter(Pattern patt){

    }
}
