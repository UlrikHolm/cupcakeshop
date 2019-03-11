package model;

public class KurveLinje {

    private int kurvelinjeID;
    private int bundID;
    private int topID;
    private int antal;
    private int stkPris;
    private int totalPris;

    public KurveLinje(int kurvelinjeID, int bundID, int topID, int antal, int stkPris, int totalPris) {
        this.kurvelinjeID = kurvelinjeID;
        this.bundID = bundID;
        this.topID = topID;
        this.antal = antal;
        this.stkPris = stkPris;
        this.totalPris = totalPris;
    }

    public KurveLinje() {
    }

    public int getKurvelinjeID() {
        return kurvelinjeID;
    }

    public void setKurvelinjeID(int kurvelinjeID) {
        this.kurvelinjeID = kurvelinjeID;
    }

    public int getBundID() {
        return bundID;
    }

    public void setBundID(int bundID) {
        this.bundID = bundID;
    }

    public int getTopID() {
        return topID;
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public int getStkPris() {
        return stkPris;
    }

    public void setStkPris(int stkPris) {
        this.stkPris = stkPris;
    }

    public int getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }
}
