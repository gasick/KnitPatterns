package com.gcorp.knitshceme;

public class Pallet {
    enum action {save, revert, undo, export};
    enum cells  {p2tls, p2trs,k2tls,k2trs,p3tls,k3tls,purl,knit,yarnover,ils,irs,kitb,pitb,empty}
    cells cell;
    cells choosenBrash;
    public cells choosenBrush()
    {
         return choosenBrash;
    }
    public void setChoosenBrash(cells c){
        choosenBrash = c;
    }
}
