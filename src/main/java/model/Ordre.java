package model;

public class Ordre {
    private int ordreID;
    private int dato;
    private int brugerID;
    private int totalSum;

    public Ordre(int ordreID, int dato, int brugerID, int totalSum) {
        this.ordreID = ordreID;
        this.dato = dato;
        this.brugerID = brugerID;
        this.totalSum = totalSum;
    }

    public int getOrdreID() {
        return ordreID;
    }

    public int getDato() {
        return dato;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public int getTotalSum() {
        return totalSum;
    }
}


