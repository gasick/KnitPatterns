package com.gcorp.knitshceme;

public class Pallet {
    enum action {save, revert, undo, export};
    enum cell  {p2tls, p2trs,k2tls,k2trs,p3tls,k3tls,purl,knit,yarnover,ils,irs,kitb,pitb,empty}
    static cell[] cells=  new cell[] {cell.p2tls, cell.p2trs,cell.k2tls,cell.k2trs,cell.p3tls,cell.k3tls,cell.purl,cell.knit,cell.yarnover,cell.ils,cell.irs,cell.kitb,cell.pitb,cell.empty};
    static cell choosenBrash = cell.empty;
    public static cell choosenBrush()
    {
         return choosenBrash;
    }
    public void setChoosenBrash(cell c){
        choosenBrash = c;
    }
    public void Pallet() {
        for (int i = 0; i< cells.length; i++)
        {


        }
    }
}
