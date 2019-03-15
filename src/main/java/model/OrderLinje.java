package model;

public class OrderLinje {
    private int ordrelinjeID;
    private int ordreID;
    private int topID;
    private int bundID;
    private int antal;
    private int prisIalt;

    public OrderLinje(int ordrelinjeID, int ordreID, int topID, int bundID, int antal, int prisIalt) {
        this.ordrelinjeID = ordrelinjeID;
        this.ordreID = ordreID;
        this.topID = topID;
        this.bundID = bundID;
        this.antal = antal;
        this.prisIalt = prisIalt;
    }

    public OrderLinje(int ordreID, int topID, int bundID, int antal, int prisIalt) {
        this.ordreID = ordreID;
        this.topID = topID;
        this.bundID = bundID;
        this.antal = antal;
        this.prisIalt = prisIalt;
    }



    public OrderLinje() {
    }

    public int getOrdrelinjeID() {
        return ordrelinjeID;
    }

    public void setOrdrelinjeID(int ordrelinjeID) {
        this.ordrelinjeID = ordrelinjeID;
    }

    public int getOrdreID() {
        return ordreID;
    }

    public void setOrdreID(int ordreID) {
        this.ordreID = ordreID;
    }

    public int getTopID() {
        return topID;
    }

    public void setTopID(int topID) {
        this.topID = topID;
    }

    public int getBundID() {
        return bundID;
    }

    public void setBundID(int bundID) {
        this.bundID = bundID;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public int getPrisIalt() {
        return prisIalt;
    }

    public void setPrisIalt(int prisIalt) {
        this.prisIalt = prisIalt;
    }
}

