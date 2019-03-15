package model;

public class KurveLinje {

    private int kurvelinjeID;
    private int bundID;
    private int topID;
    private int antal;
    private String brugerID;


    public KurveLinje(int kurvelinjeID, int bundID, int topID, int antal, int stkPris, int totalPris) {
        this.kurvelinjeID = kurvelinjeID;
        this.bundID = bundID;
        this.topID = topID;
        this.antal = antal;
        this.brugerID = brugerID;

    }



    public KurveLinje() {
    }

    @Override
    public String toString() {
        return "KurveLinje{" +
                "kurvelinjeID=" + kurvelinjeID +
                ", bundID=" + bundID +
                ", topID=" + topID +
                ", antal=" + antal +
                ", brugerID='" + brugerID + '\'' +
                '}';
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

    public String getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(String brugerID) {
        this.brugerID = brugerID;
    }
}
