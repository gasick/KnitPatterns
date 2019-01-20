package com.gcorp.knitshceme;

public class Pallet {
    enum action {save, revert, undo, export};
    public enum cell  {p2tls, p2trs,k2tls,k2trs,p3tls,k3tls,purl,knit,yarnover,ils,irs,kitb,pitb,empty}
    static cell[] cells=  new cell[] {cell.p2tls, cell.p2trs,cell.k2tls,cell.k2trs,cell.p3tls,cell.k3tls,cell.purl,cell.knit,cell.yarnover,cell.ils,cell.irs,cell.kitb,cell.pitb};


    public void Pallet() {
        for (int i = 0; i< cells.length; i++)
        {


        }
    }
}
